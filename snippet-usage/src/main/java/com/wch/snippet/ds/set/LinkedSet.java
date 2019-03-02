package com.wch.snippet.ds.set;

import com.wch.snippet.ds.link.LinkedList;

/**
 * 基于链表的Set
 *
 * @param <E>
 */
public class LinkedSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedSet() {
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        list.addLast(e);
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
