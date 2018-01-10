package com.it.project.bick.controller;

import com.it.project.bick.entity.Bick;
import com.it.project.bick.entity.BickLocation;
import com.it.project.bick.entity.Point;
import com.it.project.bick.service.BickService;
import com.it.project.bick.service.impl.BickGeoService;
import com.it.project.common.constants.Constant;
import com.it.project.common.exception.BickException;
import com.it.project.common.response.APIResult;
import com.it.project.common.rest.BaseController;
import com.it.project.record.entity.RideContrail;
import com.it.project.user.entity.UserElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fengqigui
 * @Date 2018/1/2 20:34
 */
@RestController
@RequestMapping("/bick")
public class BickController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(BickController.class);

    @Autowired
    private BickGeoService bickGeoService;

    @Autowired
    private BickService bickService;

    @RequestMapping("/findAroundBick")
    public APIResult findAroundBick(@RequestBody Point point){
        APIResult<List<BickLocation>> resp = new APIResult<>();
        try{
            /*List<BickLocation> bickList = bickGeoService.geoNear("bick_geo",point,500,10,null);*/
            List<BickLocation> bickList = bickGeoService.geoNearSphere("bick_geo","location",point,0,50000,10,null,null);
            //bickGeoService.get();
            bickGeoService.geoNear("bick_geo",point,500,10,null);
            resp.setMessage("查询附近单车成功");
            resp.setData(bickList);
        }catch (Exception e){
            LOGGER.error("查询附近单车失败");
            resp.setCode(Constant.RESP_STATUS_INTERNAL_ERROR);
            throw new RuntimeException("查询附近单车失败");
        }

        return resp;
    }

    /**
     * 解锁单车， 准备骑行
     * @param bick
     * @return
     */
    @RequestMapping("unliockBick")
    public APIResult unLockBike(@RequestBody Bick bick){


        APIResult<List<BickLocation>> resp = new APIResult<>();
        try{

            bickService.unLockBike(getCurrentUser(), bick.getBickNumber());
            resp.setMessage("");

        }catch (Exception e){
            resp.setCode(Constant.RESP_STATUS_INTERNAL_ERROR);
            LOGGER.error(e.getMessage());
            throw new RuntimeException("开锁失败");
        }




        return resp;

    }

    /**
     * 锁车，骑行结束
     * @param bickLocation
     * @return
     */
    @RequestMapping("/lockBick")
    public APIResult lockBike(@RequestBody BickLocation bickLocation) {
        APIResult<List<BickLocation>> resp = new APIResult<>();
        try {
            bickService.lockBike(bickLocation);
            resp.setMessage("锁车成功");
        } catch (Exception e) {
            LOGGER.error("Fail to lock bike", e);
            resp.setCode(Constant.RESP_STATUS_INTERNAL_ERROR);
        }
        return resp;
    }
    /**
     * 单车上报坐标
     * @param bikeLocation
     * @return
     */
    @RequestMapping("reportLocation")
    public APIResult reportLocation(@RequestBody BickLocation bikeLocation) {
        APIResult<List<BickLocation>> resp = new APIResult<>();
        try {
            bickService.reportLocation(bikeLocation);
            resp.setMessage("上报坐标成功");
        } catch (BickException e) {
            resp.setCode(e.getStatusCode());
            resp.setMessage(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Fail to report location", e);
            resp.setCode(Constant.RESP_STATUS_INTERNAL_ERROR);
            resp.setMessage("内部错误");
        }
        return resp;
    }

    /**
     * 骑行轨迹
     * @param recordNo
     * @return
     */
    @RequestMapping("contrail/{recordNo}")
    public APIResult<RideContrail> rideContrail(@PathVariable("recordNo") String recordNo) {
        APIResult<RideContrail> resp = new APIResult<>();
        try {
            UserElement userElement = getCurrentUser();
            RideContrail contrail = bickGeoService.rideContrail("ride_contrail", recordNo);
            resp.setData(contrail);
            resp.setMessage("查询成功");
        } catch (BickException e) {
            resp.setCode(e.getStatusCode());
            resp.setMessage(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Fail to query ride record", e);
            resp.setCode(Constant.RESP_STATUS_INTERNAL_ERROR);
            resp.setMessage("内部错误");
        }
        return resp;
    }
}
