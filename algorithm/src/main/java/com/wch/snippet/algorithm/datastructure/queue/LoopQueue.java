package com.wch.snippet.algorithm.datastructure.queue;

/**
 * 循环队列
 *
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {

    /**
     * 元素数据数组
     */
    private E[] data;

    /**
     * 队首元素指针
     */
    private int front;

    /**
     * 队尾元素指针
     * tail == front，队列为空
     * (tail + 1) % capacity == front，队列为满（有一个位置始终空余）
     */
    private int tail;

    /**
     * 队列元素数量
     */
    private int size;

    @SuppressWarnings("unchecked")
    public LoopQueue(int capacity) {
        this.data = (E[]) new Object[capacity + 1];
        this.front = 0;
        this.tail = 0;
        this.size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    /**
     * 队列容量
     * 实际有一个位置始终空闲，队列容量-1
     *
     * @return
     */
    int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("dequeue failed: queue is empty");
        }
        E f = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if (size == getCapacity() / 4 && 0 != getCapacity() / 2) {
            resize(getCapacity() / 2);
        }
        return f;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("dequeue failed: queue is empty");
        }
        return data[front];
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }
}
