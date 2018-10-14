package com.wch.snippet.util;

import java.io.Serializable;

public class Response<T> implements Serializable {

    private boolean success;

    private Integer code;

    private String message;

    private T data;

    private Response(boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Response buildResponse(ResponseDefinition definition) {
        return buildResponse(null, definition);
    }

    public static <T> Response buildResponse(T data, ResponseDefinition definition) {
        return new Response<>(definition.isSuccess(), definition.getCode(), definition.getMessage(), data);
    }

    public static <T> Response buildResponse(ResponseDefinition definition, String message) {
        return new Response<>(definition.isSuccess(), definition.getCode(), message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
}
