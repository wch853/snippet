package com.wch.snippet.concurrent.process;

/**
 * 标记型任务
 *
 * @param <T>
 */
public interface IdentityTask<T> extends Task<T> {

    /**
     * 任务标记
     *
     * @return
     */
    String identity();
}
