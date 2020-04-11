package com.wch.snippet.algorithm.datastructure.base;

/**
 * 链表
 *
 * @param <E>
 */
public class LinkedList<E> {

    private class Node {

        /**
         * 节点数据
         */
        public E e;

        /**
         * 下一个元素
         */
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }
    }

    /**
     * 链表的虚拟头结点
     */
    private Node dummy;

    /**
     * 链表元素个数
     */
    private int size;

    public LinkedList() {
        this.dummy = new Node(null, null);
        this.size = 0;
    }

    /**
     * 获取链表元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return 0 == size;
    }

    /**
     * 在链表中插入元素
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (0 > index || size < index) {
            throw new IllegalArgumentException("add failed: illegal index");
        }
        Node prev = dummy;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    /**
     * 在链表头部添加元素
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表末尾插入元素
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获取链表第index个元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (0 > index || size <= index) {
            throw new IllegalArgumentException("get failed: illegal index");
        }
        Node cur = dummy.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 获取头结点
     *
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取最后的节点
     *
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改节点数据
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (0 > index || size <= index) {
            throw new IllegalArgumentException("set failed: illegal index");
        }
        Node cur = dummy.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 查询链表中是否包含元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node cur = dummy.next;
        while (null != cur) {
            if (cur.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 从链表中删除元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        Node prev = dummy;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node ret = prev.next;
        prev.next = ret.next;
        ret.next = null;
        size--;
        return ret.e;
    }

    /**
     * 删除指定元素所在节点
     *
     * @param e
     */
    public void removeElement(E e) {
        Node prev = dummy;
        while (prev.next != null) {
            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    /**
     * 删除头结点元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后的元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }
}
