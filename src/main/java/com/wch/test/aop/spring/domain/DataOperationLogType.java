package com.wch.test.aop.spring.domain;

/**
 * 操作类型
 */
public enum DataOperationLogType {

    INSERT(1, "新增"),

    UPDATE(2, "修改"),

    DELETE(3, "删除");

    private int code;

    private String info;

    DataOperationLogType(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public String getInfo(int code) {
        for (DataOperationLogType dataOperationLogType : DataOperationLogType.values()) {
            if (code == dataOperationLogType.getCode()) {
                return dataOperationLogType.getInfo();
            }
        }
        return null;
    }

    public Integer getCode(String info) {
        for (DataOperationLogType dataOperationLogType : DataOperationLogType.values()) {
            if (dataOperationLogType.getInfo().equals(info)) {
                return dataOperationLogType.getCode();
            }
        }
        return null;
    }
}
