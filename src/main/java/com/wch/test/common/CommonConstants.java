package com.wch.test.common;

/**
 * 常量
 */
public interface CommonConstants {

    /**
     * amq测试队列名
     */
    String AMQ_QUEUE_TEST_NAME = "queue.test";

    /**
     * amq测试主题名
     */
    String AMQ_TOPIC_TEST_NAME = "topic.test";

    /**
     * shiro加密方式
     */
    String HASH_CREDENTIAL_NAME = "md5";

    /**
     * shiro hash次数
     */
    int HASH_ITERATIONS = 1;

    /**
     * 方法分隔符
     */
    String CLASS_METHOD_SEPERATOR = "#";

    /**
     * 换存key分隔符
     */
    String CACHE_SEPERATOR = ":";
}
