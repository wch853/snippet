package com.wch.snippet.ds.heap;

import com.wch.snippet.ds.array.Array;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * 将数组转为最大堆
     *
     * @param arr
     */
    public MaxHeap(E[] arr) {
        this.data = new Array<>(arr);
        // 最后一个非叶子节点
        int lastIndex = parent(arr.length - 1);
        for (int i = lastIndex; i >= 0; i--) {
            // 对每个非叶子节点进行下沉操作
            siftDown(i);
        }
    }

    /**
     * 堆中元素数量
     *
     * @return
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 根据当前堆元素索引返回父节点索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (0 == index) {
            throw new IllegalArgumentException("no parent");
        }
        return (index - 1) / 2;
    }

    /**
     * 根据父节点索引返回左孩子索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 根据父节点索引返回右孩子索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加节点
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(size() - 1);
    }

    /**
     * 上浮
     * 维护堆的性质，即节点值应小于其父节点的值
     *
     * @param index
     */
    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = parent(index);
            if (data.get(index).compareTo(data.get(parentIndex)) > 0) {
                // 当前节点大于父节点，应交换
                data.swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    /**
     * 找到堆中最大元素
     *
     * @return
     */
    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("empty");
        }
        return data.get(0);
    }

    /**
     * 取出堆中最大元素
     *
     * @return
     */
    public E extractMax() {
        E max = findMax();
        data.swap(0, size() - 1);
        data.removeLast();
        siftDown(0);
        return max;
    }

    /**
     * 下沉
     * 维护堆的性质，即父节点的值应大于其子节点的值
     *
     * @param index
     */
    private void siftDown(int index) {
        while (leftChild(index) < data.getSize()) {
            // 存在左孩，有下沉的必要
            int childIndex = leftChild(index);
            if (childIndex + 1 < data.getSize() && data.get(childIndex + 1).compareTo(data.get(childIndex)) > 0) {
                // 右孩合法且右孩为最大元素节点
                childIndex = childIndex + 1;
            }

            if (data.get(index).compareTo(data.get(childIndex)) > 0) {
                // 父节点大于最大孩子节点，下沉完毕
                break;
            } else {
                data.swap(index, childIndex);
                index = childIndex;
            }
        }
    }

    /**
     * 取出最大元素并放入新的元素
     *
     * @param e
     * @return
     */
    public E replace(E e) {
        E max = findMax();
        data.set(0, e);
        siftDown(0);
        return max;
    }
}
