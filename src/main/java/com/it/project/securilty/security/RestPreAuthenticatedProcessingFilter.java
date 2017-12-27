package com.it.project.securilty.security;

import com.it.project.cache.CommonCacheUtil;
import com.it.project.common.constants.Constant;
import com.it.project.user.controller.UserController;
import com.it.project.user.entity.UserElement;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义过滤器
 * @author fengqigui
 * @Date 2017/12/26 20:08
 */
public class RestPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private List<String> noneSecurityList;

    private CommonCacheUtil commonCacheUtil;

    /**
     * spring 的路径匹配器
     */
    private AntPathMatcher matcher = new AntPathMatcher();

    public RestPreAuthenticatedProcessingFilter(List<String> noneSecurityList, CommonCacheUtil commonCacheUtil) {
        this.noneSecurityList = noneSecurityList;
        this.commonCacheUtil = commonCacheUtil;
    }

    /**
     * 提取用户的信息，做预处理pre   manager  provider entrypoint
     * @param request
     * @return
     */
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        GrantedAuthority[] authorities = new GrantedAuthority[1];
        // 对于不需要过滤的URL
        if (isNoneSecurity(request.getRequestURI().toString()) || "OPTIONS".equals(request.getMethod())) {
            GrantedAuthority anthority = new SimpleGrantedAuthority("ROLE_SOMEONE");
            authorities[0] = anthority;// 角色的控制
            return new RestAuthenticationToken(Arrays.asList(authorities));
        }
        // 对于必须检查权限的URL
        // 检查APP版本
        String version = request.getHeader(Constant.REQUEST_VERSION_KEY);
        String token = request.getHeader(Constant.REQUEST_USER_TOKEN);
        // 检查version
        if (null == version) {
            request.setAttribute("header-error", 400);
        }

        // 检查token
        if ( null == request.getAttribute("header-error")) {
            try{
                if (!StringUtils.isBlank(token)) {
                    UserElement user = commonCacheUtil.getUserByToken(token);
                    if (user instanceof UserElement) {
                        // 检查到token说明用户已经登陆，授权给用户BIKE_CLIENT角色，允许访问
                        GrantedAuthority anthority = new SimpleGrantedAuthority("BIKE_CLIENT");
                        authorities[0] = anthority;// 角色的控制
                        RestAuthenticationToken authToken = new RestAuthenticationToken(Arrays.asList(authorities));
                        authToken.setUser(user);
                        return authToken;
                    } else {
                        // token 不对
                        request.setAttribute("header-error", 401);
                    }


                } else {
                    LOGGER.warn("GO to token from request header");
                    request.setAttribute("header-error", 401);
                }
            }catch (Exception e){
                LOGGER.error("Fail to authenticate user",e);
                throw new RuntimeException("security 安全异常，没有授权成功");
            }


        }
        if (request.getAttribute("header-error") != null) {
            // 请求头有错误， 随便给个角色，继续逻辑.ROLE_NONE：非正常角色
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_NONE");
            authorities[0] = authority;
        }
        RestAuthenticationToken autheToken = new RestAuthenticationToken(Arrays.asList(authorities));
        return autheToken;
    }


    /**
     * 获取用户的凭证
     * @param httpServletRequest
     * @return
     */
    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return null;
    }


    private boolean isNoneSecurity(String uri) {

        boolean result = false;
        if (null != this.noneSecurityList) {
            for (String pattern : this.noneSecurityList ) {
                if (matcher.match(pattern, uri)) {
                    result = true;
                    break;
                }

            }

        }
        return result;

    }




}
