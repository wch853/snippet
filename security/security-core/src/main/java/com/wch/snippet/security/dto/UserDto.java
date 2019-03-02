package com.wch.snippet.security.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息传输对象
 *
 * @author wch
 */
@Getter
@Setter
public class UserDto {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
