package com.it.project.user.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 保证redis存user
 * @author fengqigui
 * @Date 2017/12/21 21:50
 */
public class UserElement {

    private Integer userId;

    private String mobile;

    /**
     * 用户的token
     */
    private String token;

    /**
     * 平台：android，ios
     */
    private String platform;

    /**
     * 后端精准向单个用户推送信息
     */
    private String pushUserId;

    /**
     * 后端群组向多个用户推送信息
     */
    private String pushChannelId;

    /**
     * 从UserElement转Map
     * @return
     */
    public  Map<String, String> userElementToMap(){

        Map<String, String> map = new HashMap<>();
        map.put("userId", this.userId+"");
        map.put("mobile", this.mobile);
        map.put("token", this.token);
        map.put("platform", this.platform);
        map.put("pushChannelId", this.pushChannelId);
        if (null != this.pushUserId) {
            map.put("pushUserId", this.pushUserId);
        }
        if (null != this.pushChannelId) {
            map.put("pushUserId", this.pushChannelId);
        }
        return map;
    }


    /**
     * map 转对象
     * @param map
     * @return
     */
    public static UserElement mapToUserElement(Map<String, String> map){

        UserElement userElement = new UserElement();
        userElement.setPlatform(map.get("platform"));
        userElement.setMobile(map.get("userId"));
        userElement.setMobile(map.get("mobile"));
        userElement.setMobile(map.get("token"));
        userElement.setMobile(map.get("pushChannelId"));
        userElement.setMobile(map.get("pushUserId"));
        return userElement;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPushUserId() {
        return pushUserId;
    }

    public void setPushUserId(String pushUserId) {
        this.pushUserId = pushUserId;
    }

    public String getPushChannelId() {
        return pushChannelId;
    }

    public void setPushChannelId(String pushChannelId) {
        this.pushChannelId = pushChannelId;
    }





}
