package com.it.project.record.service.impl;

import com.it.project.record.dao.RideRecordMapper;
import com.it.project.record.entity.RideRecord;
import com.it.project.record.service.RideRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fengqigui
 * @Date 2018/1/9 20:32
 */
@Service("rideRecordServiceImpl")
public class RideRecordServiceImpl implements RideRecordService{
    @Autowired
    private RideRecordMapper rideRecordMapper;
    @Override
    public List<RideRecord> listRideRecord(Integer userId, Long lastId) {
        List<RideRecord> list = rideRecordMapper.selectRideRecordPage(userId, lastId);
        return list;
    }
}
