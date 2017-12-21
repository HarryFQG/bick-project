package com.it.project.user.entity;

/**
 *
 *
 * @author fengqigui
 * @Date 2017/12/21 18:49
 */
public class LoginInfo {

    /**
     * 密文，前端传输过来的数据
     */
    private String data;

    /**
     * 前端加密明文的AES的Key
     */
    private String AESkey;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAESkey() {
        return AESkey;
    }

    public void setAESkey(String AESkey) {
        this.AESkey = AESkey;
    }
}
