package com.it.project.securilty.security;

import org.springframework.security.core.AuthenticationException;

/**
 * @author fengqigui
 * @Date 2017/12/27 19:17
 */
public class BadCredentialException extends AuthenticationException {
    public BadCredentialException(String msg) {
        super(msg);
    }
}
