package com.it.project.securilty.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * 这个类可以有一到N个，
 * @author fengqigui
 * @Date 2017/12/26 20:12
 */
public class RestAuthenticatedProvider implements AuthenticationProvider{

    /**
     * 给用户授权的过程
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("----------22222222-----------");
        if(authentication instanceof PreAuthenticatedAuthenticationToken) {
            PreAuthenticatedAuthenticationToken preToken = (PreAuthenticatedAuthenticationToken) authentication;
            RestAuthenticationToken sysAuth = (RestAuthenticationToken)preToken.getPrincipal();
            if(sysAuth.getAuthorities() != null  && sysAuth.getAuthorities().size() > 0) {
                GrantedAuthority gauth = sysAuth.getAuthorities().iterator().next();
                if("BIKE_CLIENT".equals(gauth.getAuthority())) {
                    return sysAuth;
                }else if ("ROLE_SOMEONE".equals(gauth.getAuthority())) {
                    return sysAuth;
                }
            }

        }else if(authentication instanceof RestAuthenticationToken) {
            RestAuthenticationToken sysAuth = (RestAuthenticationToken)authentication;
            if(sysAuth.getAuthorities() != null  && sysAuth.getAuthorities().size() > 0) {
                GrantedAuthority gauth = sysAuth.getAuthorities().iterator().next();
                if("BIKE_CLIENT".equals(gauth.getAuthority())) {
                    return sysAuth;
                }else if ("ROLE_SOMEONE".equals(gauth.getAuthority())) {
                    return sysAuth;
                }
            }

        }
        throw  new BadCredentialException("unknown.error");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("-----------33333-------------");
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication) || RestAuthenticationToken.class.isAssignableFrom(authentication);
    }




}
