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

    /*-------------短信验证信息开始------------*/

    /*-------------短信验证信息------------*/

}
