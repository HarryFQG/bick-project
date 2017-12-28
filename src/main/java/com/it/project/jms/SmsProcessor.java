package com.it.project.jms;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.it.project.sms.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;


/**
 * 短信验证的生产者
 * @author fengqigui
 * @Date 2017/12/28 21:24
 */
@Component("smsProcessor")
public class SmsProcessor {

    /*直接注入*/
    @Autowired
    private JmsMessagingTemplate jms;

    @Autowired
    @Qualifier("verCodeService")
    private SmsSender smsSend;


    /**
     * 生产者用于生产消息
     * @param destination
     * @param message
     */
    public void sendSmsToQueue(Destination destination, final String message){
        jms.convertAndSend( destination, message);
    }

    /**
     * 消费者,队列的监听者
     * @param text
     * 监听的队列是：sms.queue
     */
    @JmsListener(destination = "sms.queue")
    public void doSendSmsMessage(String text) {
        JSONObject jsonObject = JSON.parseObject(text);
        smsSend.sendSms(jsonObject.getString("mobile"),jsonObject.getString("tplId"),jsonObject.getString("vercode"));

    }

}
