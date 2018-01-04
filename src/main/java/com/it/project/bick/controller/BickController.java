package com.it.project.bick.controller;

import com.it.project.bick.entity.BickLocation;
import com.it.project.bick.entity.Point;
import com.it.project.bick.service.impl.BickGeoService;
import com.it.project.common.constants.Constant;
import com.it.project.common.response.APIResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BickController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BickController.class);

    @Autowired
    private BickGeoService bickGeoService;


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


}
