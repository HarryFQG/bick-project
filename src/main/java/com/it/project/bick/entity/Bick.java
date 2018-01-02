package com.it.project.bick.entity;

public class Bick {
    private Long bickId;

    private Long bickNumber;

    private Byte bickType;

    private Byte bickEnableuse;

    public Long getBickId() {
        return bickId;
    }

    public void setBickId(Long bickId) {
        this.bickId = bickId;
    }

    public Long getBickNumber() {
        return bickNumber;
    }

    public void setBickNumber(Long bickNumber) {
        this.bickNumber = bickNumber;
    }

    public Byte getBickType() {
        return bickType;
    }

    public void setBickType(Byte bickType) {
        this.bickType = bickType;
    }

    public Byte getBickEnableuse() {
        return bickEnableuse;
    }

    public void setBickEnableuse(Byte bickEnableuse) {
        this.bickEnableuse = bickEnableuse;
    }
}