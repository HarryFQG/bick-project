package com.it.project.sms;

/**
 * 发送短信的接口，以免日后换了短信提供商要改源码
 * @author fengqigui
 * @Date 2017/12/28 20:31
 */
public interface SmsSender {

    /**
     *
     * @param phone :电话号码
     * @param tplId ：模板号
     * @param params ：参数
     */
    void sendSms(String phone, String tplId, String params);

}
