package com.it.project.common.util;

import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.it.project.common.constants.Constant;
import com.it.project.common.exception.BickException;
import com.it.project.user.entity.UserElement;

/**
 * @author fengqigui
 * @Date 2018/1/8 19:36
 */
public class BaiduPushUtil {

    public static void pushMsgToSingleDevice(UserElement userElement, String message) throws Exception {
        PushKeyPair pair = new PushKeyPair(Constant.BAIDU_YUN_PUSH_API_KEY, Constant.BAIDU_YUN_PUSH_SECRET_KEY);
        BaiduPushClient pushClient = new BaiduPushClient(pair, Constant.BAIDU_YUN_PUSH_CHANNEL_URL);
        try {
            // 设置请求参数，创建请求实例
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
                    .addChannelId(userElement.getPushChannelId())
                    .addMsgExpires(3600)                            // 设置消息的有效时间，单位秒，默认3600
                    .addMessageType(1)                              // 设置消息类型，0表示透传消息，1表示通知消息，默认为0
                    .addMessage(message);

            // 设置设备类型 deviceType => 1 for web ,2 for pc ,3 for android ,4 for ios ,5 for wp
            if ("android".equals(userElement.getPlatform())) {
                request.setDeviceType(3);
            } else if ("ios".equals(userElement.getPlatform())) {
                request.setDeviceType(4);
            }
            PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
        } catch (PushClientException | PushServerException e) {
            e.printStackTrace();
            throw new BickException(e.getMessage());
        }
    }

}
