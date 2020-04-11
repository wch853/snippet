package com.wch.snippet.util;

public enum ResponseDefinition {

    SUCCESS(Boolean.TRUE, 200, "成功"),
    ERROR(Boolean.FALSE, 500, "失败");

    private boolean success;

    private Integer code;

    private String message;

    ResponseDefinition(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
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
}
