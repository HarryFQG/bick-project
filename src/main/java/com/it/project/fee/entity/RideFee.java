package com.it.project.fee.entity;

public class RideFee {
    private Long id;

    private Integer minUnit;

    private Long fee;

    private Byte bickType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(Integer minUnit) {
        this.minUnit = minUnit;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Byte getBickType() {
        return bickType;
    }

    public void setBickType(Byte bickType) {
        this.bickType = bickType;
    }
}