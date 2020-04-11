package com.wch.snippet.algorithm.datastructure.stack;

import com.wch.snippet.algorithm.datastructure.base.LinkedList;

/**
 * 基于链表的栈
 *
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedStack() {
        this.list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getLast();
    }

}
