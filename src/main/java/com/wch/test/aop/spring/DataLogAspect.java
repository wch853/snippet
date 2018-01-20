package com.wch.test.aop.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLogAspect.class);

    @Pointcut("execution(public * com.wch.test.aop.spring.*.save*(..))")
    public void save() {
    }

    @Pointcut("execution(public * com.wch.test.aop.spring.*.delete(..))")
    public void delete() {
    }

    @Around("save() || delete()")
    public Object addOperation(ProceedingJoinPoint pjp) {
        LOGGER.info("enter data log aspect");
        // 获取切入方法所在的目标对象
        Object target = pjp.getTarget();
        // 获取目标对象的类对象
        Class<?> targetClass = target.getClass();
        // 切入方法名
        String methodName = pjp.getSignature().getName();
        // 切入方法的参数
        Object[] args = pjp.getArgs();

        Object result = null;
        return result;
    }
}
