package com.it.project.record.service;

import com.it.project.record.entity.RideRecord;

import java.util.List;

/**
 * @author fengqigui
 * @Date 2018/1/9 20:31
 */
public interface RideRecordService {

    List<RideRecord> listRideRecord(Integer userId, Long lastId);
}
