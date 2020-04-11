package com.wch.shiro.util;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @param <T>
 */
@Data
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

    public static Response buildResponse(ResponseDefinition definition, String message) {
        return new Response<>(definition.isSuccess(), definition.getCode(), message, null);
    }
}
