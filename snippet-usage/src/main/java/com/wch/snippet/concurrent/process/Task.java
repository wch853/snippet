package com.wch.snippet.concurrent.process;

/**
 * 任务
 *
 * @param <T>
 */
public interface Task<T> {

    /**
     * 执行任务
     *
     * @return
     * @throws Exception
     */
    T execute() throws Exception;
}
