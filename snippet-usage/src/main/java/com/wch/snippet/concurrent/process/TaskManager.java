package com.wch.snippet.concurrent.process;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 任务管理器
 */
public class TaskManager {

    /**
     * CPU核数
     */
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    /**
     * 默认核心线程数
     */
    private static final int DEFAULT_CORE_POOL_SIZE = AVAILABLE_PROCESSORS * 2;

    /**
     * 默认最大线程数
     */
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = AVAILABLE_PROCESSORS * 2;

    private static final String DEFAULT_BUSINESS_DOMAIN = "DefaultBusinessDomain";

    /**
     * 处理器容器
     * 按照业务域区分
     */
    private static final Map<String, TaskProcess> PROCESS_CONTAINER = new ConcurrentHashMap<>();

    private static TaskProcess getTaskProcess(Integer corePoolSize, Integer maximumSize, String businessDomain) {
        TaskProcess taskProcess = PROCESS_CONTAINER.get(businessDomain);
        if (null == taskProcess) {
            // 双重检测
            synchronized (TaskManager.class) {
                taskProcess = PROCESS_CONTAINER.get(businessDomain);
                if (null == taskProcess) {
                    if (null == corePoolSize || null == maximumSize) {
                        corePoolSize = DEFAULT_CORE_POOL_SIZE;
                        maximumSize = DEFAULT_MAXIMUM_POOL_SIZE;
                    }
                    taskProcess = new TaskProcess(corePoolSize, maximumSize, businessDomain);
                    PROCESS_CONTAINER.put(businessDomain, taskProcess);
                }
            }
        }
        return taskProcess;
    }

    public static TaskProcess getDefaultTaskProcess() {
        return getTaskProcess(32, 32, DEFAULT_BUSINESS_DOMAIN);
    }

}
