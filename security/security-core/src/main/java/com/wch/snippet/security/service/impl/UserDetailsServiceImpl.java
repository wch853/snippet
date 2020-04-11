package com.wch.snippet.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 通过用户名获取用户信息 - spring security扩展
 *
 * @author wch
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * 初始化一次，否则会因为生成不同的盐导致加密的密码不一样
     */
    private static final String PASS = new BCryptPasswordEncoder().encode("123");

    /**
     * 查询用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException 支持在查询不到用户信息时返回 UsernameNotFoundException 异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, PASS, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
