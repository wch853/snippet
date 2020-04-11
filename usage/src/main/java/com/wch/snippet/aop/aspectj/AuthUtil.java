package com.wch.snippet.aop.aspectj;

import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    /**
     * 验证用户
     */
    public void checkAccess() {
        String user = CurrentUserHolder.get();
        if (!"admin".equals(user)) {
            System.out.println("check access failed.");
            throw new RuntimeException("domain not allow.");
        }
        System.out.println("check access success.");
    }
}
