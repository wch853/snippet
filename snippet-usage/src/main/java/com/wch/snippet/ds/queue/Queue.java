package com.wch.snippet.ds.queue;

/**
 * 队列
 *
 * @param <E>
 */
public interface Queue<E> {

    /**
     * 查询队列元素数量
     *
     * @return
     */
    int getSize();

    /**
     * 队列是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 元素入队
     *
     * @param e
     */
    void enqueue(E e);

    /**
     * 元素出队
     *
     * @return
     */
    E dequeue();

    /**
     * 查看队首元素
     *
     * @return
     */
    E getFront();
}
