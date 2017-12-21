package com.it.project.common.exception;

import com.it.project.common.constants.Constant;

/**
 * 自定义异常统一处理
 * @author fengqigui
 * @Date 2017/12/21 19:02
 */
public class BickException extends Exception {

    public BickException (String message) {

        super(message);

    }

    public int getStatusCode () {

        return Constant.RESP_STATUS_INTERNAL_ERROR;

    }

}
