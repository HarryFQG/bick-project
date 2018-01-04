package com.it.project.bick.entity;

/**
 * mongodb
 * 单车的位置
 * @author fengqigui
 * @Date 2018/1/3 19:04
 */
public class BickLocation {

    private String id;

    /**
     * 单车的编号
     */
    private Long bickNum;

    /**
     * 单车的状态：1、2
     */
    private int status;
    /**
     * 位置
     */
    private Double[] coordinates;

    /**
     * 精确的范围
     */
    private Double distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getBickNum() {
        return bickNum;
    }

    public void setBickNum(Long bickNum) {
        this.bickNum = bickNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
