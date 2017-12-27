package com.it.project.securilty.security;

import com.alibaba.fastjson.JSON;
import com.it.project.common.response.APIResult;
import com.it.project.user.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一异常处理类
 * @author fengqigui
 * @Date 2017/12/26 20:18
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * 异常时统一返回结果
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        System.out.println("----------4444----");
        APIResult result =  new APIResult();
        // 检查头部错误
        if (request.getAttribute("header-error") != null) {
            if("400".equals(request.getAttribute("header-error") + "")){
                result.setCode(408);
                result.setMessage("请升级APP至最新版");
            } else {
                result.setCode(401);
                result.setMessage("请先登录");
            }

        }
        try{
            // 设置跨域请求 ，请求结果json刷到响应里面，前后端分离是都得这么设置
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Allow-Methods","POST, Get, OPTIONS, DELETE, HEADER");
            response.setHeader("Access-Control-Max-Age","3600");
            response.setHeader("Access-Control-Allow-Headers","X-Requested-With, user-token, Content-Type,Origin,accept");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(JSON.toJSONString(result));
            response.flushBuffer();
        } catch (Exception er) {
            LOGGER.error("Fail to send 401 response{}",er.getMessage());
            throw  new RuntimeException("请求发送错误");
        }

    }
}
