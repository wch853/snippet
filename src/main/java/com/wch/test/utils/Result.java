package com.wch.test.utils;

import com.wch.test.enums.ResultEnum;

/**
 * 统一返回结果
 */
public class Result<T> {

    /**
     * 结果代码
     */
    private int code;

    /**
     * 消息描述
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result response(ResultEnum resultEnum, String message, T data) {
        return new Result<>(resultEnum.getCode(), message, data);
    }

    public static Result response(ResultEnum resultEnum) {
        return new Result<>(resultEnum.getCode(), null, null);
    }
}
