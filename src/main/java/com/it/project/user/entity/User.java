package com.it.project.user.entity;

public class User {
    private Integer userId;

    private String userName;

    private String userMobile;

    private String userPhoto;

    private Integer userFlag;

    private Integer userEnableflag;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto == null ? null : userPhoto.trim();
    }

    public Integer getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(Integer userFlag) {
        this.userFlag = userFlag;
    }

    public Integer getUserEnableflag() {
        return userEnableflag;
    }

    public void setUserEnableflag(Integer userEnableflag) {
        this.userEnableflag = userEnableflag;
    }
}