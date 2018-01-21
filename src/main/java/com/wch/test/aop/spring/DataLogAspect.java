package com.wch.test.aop.spring;

import com.wch.test.aop.spring.dao.DataOperationLogRepository;
import com.wch.test.aop.spring.domain.*;
import com.wch.test.utils.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 日志切面，将指定dao增删改操作写入数据库
 */
@Aspect
@Component
public class DataLogAspect {

    @Resource
    private DataOperationLogRepository dataOperationLogRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLogAspect.class);

    /**
     * ProductOrderRepository#save(ProductOrder productOrder)保存方法名（新增、修改）
     * （继承自org.springframework.data.repository.CrudRepository）
     */
    private static final String SAVE_METHOD_NAME = "save";

    /**
     * ProductOrderRepository#delete(Long id)删除方法名
     * （继承自org.springframework.data.repository.CrudRepository）
     */
    private static final String DELETE_METHOD_NAME = "delete";

    /**
     * 非法ID
     */
    private static final Long INVALID_ID = 0L;

    /**
     * 匹配 ProductOrderRepository#save(ProductOrder productOrder)
     */
    @Pointcut("execution(* com.wch.test.aop.spring.dao.ProductOrderRepository.save(..))")
    public void saveByEntity() {
    }

    /**
     * 匹配 ProductOrderRepository#delete(Long id)
     */
    @Pointcut("execution(* com.wch.test.aop.spring.dao.ProductOrderRepository.delete(..))")
    public void deleteById() {
    }

    /**
     * 针对ProductOrderRepository中两个方法的执行做日志记录
     *
     * @param pjp 连接点
     * @return 目标方法返回值
     */
    @Around("saveByEntity() || deleteById()")
    public Object addOperation(ProceedingJoinPoint pjp) {
        LOGGER.info("enter data log aspect");
        Object returnValue = null;
        try {
            // 获取切入方法所在的目标对象(ProductOrderRepository)
            Object target = pjp.getTarget();
            // 切入方法名
            String methodName = pjp.getSignature().getName();
            // 切入方法的首个参数
            Object firstArg = pjp.getArgs()[0];
            // 操作记录对象
            DataOperationLog dataOperationLog = new DataOperationLog();
            dataOperationLog.setOperateMethod(methodName);

            /*
             * 组装日志记录对象
             */
            if (SAVE_METHOD_NAME.equals(methodName)) {
                // 保存方法，参数为ProductOrder对象
                ProductOrder productOrder = (ProductOrder) firstArg;
                Long id = productOrder.getId();
                if (null == id || INVALID_ID.equals(id)) {
                    // 新增
                    List<ChangeItem> insertChangeItems = FieldValueDiffUtils.getInsertChangeItems(productOrder);
                    dataOperationLog.setDataOperationLogType(DataOperationLogType.INSERT);
                    dataOperationLog.setChangeItems(insertChangeItems);
                } else {
                    // 修改
                    Object oldObject = FieldValueDiffUtils.getOldObject(target, id);
                    List<ChangeItem> updateChangeItems = FieldValueDiffUtils.getUpdateChangeItems(oldObject, productOrder);
                    dataOperationLog.setDataOperationLogType(DataOperationLogType.UPDATE);
                    dataOperationLog.setDataId(id);
                    dataOperationLog.setChangeItems(updateChangeItems);
                }
            } else if (DELETE_METHOD_NAME.equals(methodName)) {
                // 删除方法
                Long id = (Long) firstArg;
                Object oldObject = FieldValueDiffUtils.getOldObject(target, id);
                List<ChangeItem> deleteChangeItems = FieldValueDiffUtils.getDeleteChangeItems(oldObject);
                dataOperationLog.setDataOperationLogType(DataOperationLogType.DELETE);
                dataOperationLog.setDataId(id);
                dataOperationLog.setChangeItems(deleteChangeItems);
            }

            // 执行目标方法
            returnValue = pjp.proceed(pjp.getArgs());

            if (DataOperationLogType.INSERT.getCode() == dataOperationLog.getDataOperationLogType()) {
                // 新增操作赋新增记录id
                ProductOrder productOrder = (ProductOrder) returnValue;
                dataOperationLog.setDataId(productOrder.getId());
            }

            dataOperationLog.setOperateTime(new Date());
            // 进行日志数据保存
            dataOperationLogRepository.save(dataOperationLog);
            LOGGER.info("log data operation: {}", JsonUtils.toJsonString(dataOperationLog));
        } catch (Throwable e) {
            LOGGER.error("data log operation exception: {}", e.getMessage());
        }
        return returnValue;
    }
}
