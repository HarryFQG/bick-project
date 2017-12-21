package com.it.project.common.response;

import com.it.project.common.constants.Constant;

/**
 * 用于请求后返回统一数据格式
 * @author fengqigui
 * @Date 2017/12/21 18:31
 * @param <T>
 *           请求的数据类型，也可以直接使用Object
 */
public class APIResult <T>{

    /**
     * 返回状态码：200正常
     */
    private int code = Constant.RESP_STATUS_OK;

    /**
     * 返回的信息
     */
    private String message;

    /**
     *请求的数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
