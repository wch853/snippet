package com.wch.snippet.security.verification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 验证码
 *
 * @author wch
 */
@Getter
@Setter
@AllArgsConstructor
public class VerificationCode {

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    public LocalDateTime expireTime;

    VerificationCode(String code, int expireIn) {
        this(code, LocalDateTime.now().plusSeconds(expireIn));
    }

    /**
     * 判断当前验证码是否已经过期
     *
     * @return
     */
    public boolean isExpired() {
        return null != expireTime && LocalDateTime.now().isAfter(expireTime);
    }
}
