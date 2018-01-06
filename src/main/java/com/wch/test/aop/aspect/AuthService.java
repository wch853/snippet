package com.wch.test.aop.aspect;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    /**
     * 验证用户
     */
    public void checkAccess() {
        String user = CurrentUserHolder.get();
        if (!"admin".equals(user)) {
            throw new RuntimeException("operation not allow.");
        }
    }
}
