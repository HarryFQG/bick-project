package com.it.project.common.constants;

/**
 * 常量包
 * 常量类
 * @author fengqigui
 * @Date 2017/12/21 18:38
 */
public class Constant {

    /**---自定义状态码 start----**/
    public static final int RESP_STATUS_OK = 200;

    /**
     * 没有授权
     */
    public static final int RESP_STATUS_NOAUTH = 401;

    /**
     *内部错误
     */
    public static final int RESP_STATUS_INTERNAL_ERROR = 500;

    /**
     * 错误请求
     */
    public static final int RESP_STATUS_BADREQUEST = 400;


    /**---自定义状态码 end----**/
    /**
     * 请求头的名字
     */
    public static final String REQUEST_USER_TOKEN = "user_token";

    /**
     * APP的版本
     */
    public static final String REQUEST_VERSION_KEY = "version";

    /*-------------秒滴SMS发短信start------------*/
    public static final String MDSMS_ACCOUT_SID = "f7440674b9a74038b99203ecd48b3124";

    public static final String MDSMS_AUTH_TOKEN = "15791718ba9348759ffeb53c39982cee";

    /**
     * 请求的URL
     */
    public static final String MDSMS_REST_URL = "https://api.miaodiyun.com/20150822";

    /**
     * 短信模板ID
     */
    public static final String MDSMS_VERCODE_TPKID = "132422946";
    /*--------------发短信end------------*/


    /*--------------百度云推送----------*/
    public static final String BAIDU_YUN_PUSH_SECRET_KEY="ANcqtKO100XnjhBIPIkjAxehYsldDEAi";

    public static final String BAIDU_YUN_PUSH_API_KEY="HFEQ2EyIxaFEAza33U8Ljn0B";

    public static final String BAIDU_YUN_PUSH_CHANNEL_URL="";


    /*-------------配置结束---------*/


}
