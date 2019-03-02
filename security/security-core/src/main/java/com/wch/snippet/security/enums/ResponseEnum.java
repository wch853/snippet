package com.wch.snippet.security.enums;

import lombok.Getter;

/**
 * 响应枚举
 *
 * @author wch
 */
@Getter
public enum ResponseEnum {

    /**
     * 成功
     */
    SUCCESS(true, 2000, "成功"),

    /**
     * 无权限
     */
    UNAUTHORIZED(false, 4100, "认证错误"),

    /**
     * 无权限
     */
    FORBIDDEN(false, 4300, "无权限"),

    /**
     * 失败
     */
    FAIL(false, 5000, "失败"),

    /**
     * 服务器内部错误
     */
    SERVER_INTERNAL_ERROR(false, 5100, "服务器内部错误"),

    /**
     * 参数错误
     */
    INVALID_PARAM(false, 5200, "参数错误");

    /**
     * 接口调用成功标志
     */
    private Boolean success;

    /**
     * 接口调用状态码
     */
    private int code;

    /**
     * 接口调用消息提示
     */
    private String msg;

    ResponseEnum(boolean success, int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

}
