package com.wch.snippet.ds.stack;

/**
 * 栈
 *
 * @param <E>
 */
public interface Stack<E> {

    /**
     * 查询栈中元素数量
     *
     * @return
     */
    int getSize();

    /**
     * 是否为空栈
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 向栈顶添加元素
     *
     * @param e
     */
    void push(E e);

    /**
     * 元素出栈
     *
     * @return
     */
    E pop();

    /**
     * 查看栈顶元素
     *
     * @return
     */
    E peek();
}
