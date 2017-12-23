package com.it.project.common.rest;

import com.it.project.cache.CommonCacheUtil;
import com.it.project.common.constants.Constant;
import com.it.project.user.entity.UserElement;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);


    @Autowired
    private CommonCacheUtil cacheUtil;

    /**
     * 拿用户的token，取redis中用户的信息
     * 这个token放在请求头里面的
     */
    protected UserElement getCurrentUser() {

        // 需要将springmvc的servlet的request，强转为servlet的request.
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        // header 的名字是user-token，值就是我们的token。每次放的时候，都是user-token
        String token = request.getHeader(Constant.REQUEST_USER_TOKEN);
        if (!StringUtils.isBlank(token)) {
            try {
                UserElement userElement = cacheUtil.getUserByToken(token);
                return userElement;
            } catch (Exception e) {
                LOGGER.error("fail to get user by token");
                throw e;
            }
        }
        return null;

    }

}
