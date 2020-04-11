package com.wch.snippet.algorithm.datastructure.queue;

/**
 * 基于链表的队列
 *
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {

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
     * 头结点
     */
    private Node head;

    /**
     * 尾节点
     */
    private Node tail;

    /**
     * 队列元素数量
     */
    private int size;

    public LinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }

    @Override
    public void enqueue(E e) {
        if (null == head) {
            head = new Node(e);
            tail = head;
        } else {
            Node newNode = new Node(e);
            tail.next = newNode;
            tail = newNode;
        }
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("dequeue failed, queue is empty.");
        }
        Node node = head;
        head = node.next;
        node.next = null;
        if (null == head) {
            tail = null;
        }
        size--;
        return node.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("getFront failed, queue is empty.");
        }
        return head.e;
    }
}
