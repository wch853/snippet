package com.wch.test.aop.spring.dao;

import com.wch.test.aop.spring.domain.DataOperationLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 操作记录保存dao
 */
public interface DataOperationLogRepository extends JpaRepository<DataOperationLog, Long> {
}
