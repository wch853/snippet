package com.wch.test.aop.spring.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wch.test.utils.JsonUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * 日志记录
 */
@Entity
@Table(name = "data_operation_log")
public class DataOperationLog {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 数据操作记录来源
     */
    private String operateMethod;

    /**
     * 被操作的数据记录id
     */
    private Long dataId;

    /**
     * 数据操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operateTime;

    /**
     * 数据操作类型
     */
    private int dataOperationLogType;

    /**
     * 数据操作变更项集合
     */
    private String changeItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateMethod() {
        return operateMethod;
    }

    public void setOperateMethod(String operateMethod) {
        this.operateMethod = operateMethod;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public int getDataOperationLogType() {
        return dataOperationLogType;
    }

    public void setDataOperationLogType(DataOperationLogType dataOperationLogType) {
        this.dataOperationLogType = dataOperationLogType.getCode();
    }

    public String getChangeItems() {
        return changeItems;
    }

    public void setChangeItems(List<ChangeItem> changeItems) {
        this.changeItems = JsonUtils.toJsonString(changeItems);
    }
}
