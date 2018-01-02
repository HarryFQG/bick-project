package com.it.project.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.it.project.common.constants.Constant;
import com.it.project.securilty.MD5Util;
import com.it.project.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 秒滴发送短信的实现类
 * @author fengqigui
 * @Date 2017/12/28 20:34
 */
@Service("verCodeService")
public class MiaoDiSmsSender implements SmsSender{

    private static final Logger LOGGER = LoggerFactory.getLogger(MiaoDiSmsSender.class);
    /**
     * 发送短信的后缀
     */
    private static String operation = "/industrySMS/sendSMS";
    @Override
    public  void sendSms(String phone, String tplId, String params) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestamp = sdf.format(new Date());
            String sig = MD5Util.getMD5(Constant.MDSMS_ACCOUT_SID+Constant.MDSMS_AUTH_TOKEN+timestamp);
            String url = Constant.MDSMS_REST_URL+operation;
            Map<String, String> param = new HashMap<>();
            param.put("accountSid",Constant.MDSMS_ACCOUT_SID);
            param.put("to",phone);
            param.put("templateid",tplId);
            param.put("param",params);
            param.put("timestamp",timestamp);
            param.put("sig",sig);
            param.put("respDataType","json");
            String result = HttpUtil.post(url,param);
            JSONObject jsonObject = JSON.parseObject(result);
            if(jsonObject.getString("respCode").equals("0000")){
                LOGGER.error("fail to send sms to"+phone+":"+params+"："+result);

            }
        }catch (Exception e){
            LOGGER.error("fail to send sms to"+ phone+":"+params);
        }



    }

}
