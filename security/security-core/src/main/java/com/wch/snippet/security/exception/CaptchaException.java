package com.wch.snippet.security.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * 验证码认证异常
 *
 * @author wch
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg, Throwable t) {
        super(msg, t);
    }

    public CaptchaException(String msg) {
        super(msg);
    }
}
