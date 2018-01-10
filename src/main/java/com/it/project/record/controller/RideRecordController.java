package com.it.project.record.controller;

import com.it.project.bick.service.impl.BickGeoService;
import com.it.project.common.constants.Constant;
import com.it.project.common.exception.BickException;
import com.it.project.common.response.APIResult;
import com.it.project.common.rest.BaseController;
import com.it.project.record.entity.RideRecord;
import com.it.project.record.service.RideRecordService;
import com.it.project.user.entity.UserElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 骑行记录
 * @author fengqigui
 * @Date 2018/1/9 20:27
 */
@RestController
@RequestMapping("rideRecord")
public class RideRecordController extends BaseController{
    @Autowired
    @Qualifier("rideRecordServiceImpl")
    private RideRecordService rideRecordService;

    @Autowired
    private BickGeoService bikeGeoService;

    @RequestMapping("list/{id}")
    public APIResult<List<RideRecord>> listRideRecord(@PathVariable("id") Long lastId) {
        APIResult<List<RideRecord>> resp = new APIResult<>();
        try {
            UserElement userElement = getCurrentUser();
            List<RideRecord> list = rideRecordService.listRideRecord(userElement.getUserId(), lastId);
            resp.setData(list);
            resp.setMessage("查询成功");
        }  catch (Exception e) {

            resp.setCode(Constant.RESP_STATUS_INTERNAL_ERROR);
            resp.setMessage("内部错误");
        }
        return resp;
    }
}
