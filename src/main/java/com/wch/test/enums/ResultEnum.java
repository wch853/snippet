package com.wch.test.enums;

public enum  ResultEnum {

    /**
     * 成功
     */
    SUCCESS(200),

    /**
     * 失败
     */
    FAIL(300),

    /**
     * 未授权
     */
    UNAUTHORIZED(403);

    /**
     * 返回代码
     */
    private Integer code;

    ResultEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
