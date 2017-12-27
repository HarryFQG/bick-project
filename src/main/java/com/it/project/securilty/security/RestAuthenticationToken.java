package com.it.project.securilty.security;

import com.it.project.user.entity.UserElement;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 *spring security 里面的filter使用
 * @author fengqigui
 * @Date 2017/12/26 21:10
 */
public class RestAuthenticationToken extends AbstractAuthenticationToken{

    private UserElement user;
    /**
     * GrantedAuthority：里面放的是用户的角色
     * @param authorities
     */
    public RestAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public void setUser(UserElement user) {
        this.user = user;
    }

    public UserElement getUser() {
        return user;
    }
}
