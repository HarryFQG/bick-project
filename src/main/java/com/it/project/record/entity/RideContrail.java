package com.it.project.record.entity;

import com.it.project.bick.entity.Point;

import java.util.List;

/**
 * @author fengqigui
 * @Date 2018/1/10 19:56
 */
public class RideContrail {
    private String rideRecordNo;

    private Long bikeNo;

    private List<Point> contrail;

    public String getRideRecordNo() {
        return rideRecordNo;
    }

    public void setRideRecordNo(String rideRecordNo) {
        this.rideRecordNo = rideRecordNo;
    }

    public Long getBikeNo() {
        return bikeNo;
    }

    public void setBikeNo(Long bikeNo) {
        this.bikeNo = bikeNo;
    }

    public List<Point> getContrail() {
        return contrail;
    }

    public void setContrail(List<Point> contrail) {
        this.contrail = contrail;
    }
}
