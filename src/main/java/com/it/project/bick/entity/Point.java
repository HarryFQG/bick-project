package com.it.project.bick.entity;

/**
 * 经纬度，坐标类
 * @author fengqigui
 * @Date 2018/1/3 19:09
 */
public class Point {
    private double longitude;

    private double latitude;

    public Point() {
    }

    public Point(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
