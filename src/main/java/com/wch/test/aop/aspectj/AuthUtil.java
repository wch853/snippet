package com.wch.test.aop.aspectj;

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
            throw new RuntimeException("operation not allow.");
        }
        System.out.println("check access success.");
    }
}
