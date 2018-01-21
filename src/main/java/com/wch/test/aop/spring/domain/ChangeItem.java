package com.wch.test.aop.spring.domain;

/**
 * 变更项
 */
public class ChangeItem {

    /**
     * 变更属性名
     */
    private String field;

    /**
     * 变更旧值
     */
    private String oldValue;

    /**
     * 变更新值
     */
    private String newValue;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
