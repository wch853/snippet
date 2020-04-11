package com.wch.snippet.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 安全管理-接口
 *
 * @author wch
 */
@RestController
public class SecurityController {

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/user")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }
}
