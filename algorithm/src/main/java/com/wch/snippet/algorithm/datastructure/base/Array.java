package com.wch.snippet.algorithm.datastructure.base;

/**
 * 数组
 *
 * @param <E>
 * @author wch
 */
public class Array<E> {

    private E[] data;

    /**
     * 数组元素个数，也指向第一个没有元素的位置
     */
    private int size;

    @SuppressWarnings("unchecked")
    public Array(int capacity) {
        this.data = (E[]) new Object[capacity];
    }

    public Array() {
        this(10);
    }

    public Array(E[] arr) {
        this.data = arr;
        this.size = arr.length;
    }

    /**
     * 数组元素数量
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 数组容量
     *
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 数组判空
     *
     * @return
     */
    public boolean isEmpty() {
        return 0 == size;
    }

    /**
     * 数组向指定索引位置插入元素，此索引对应的原有元素至末端后移
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (0 > index || index > size) {
            // 插入的位置不能不存在，也不能使得中间出现空余位置
            throw new IllegalArgumentException("base add failed: illegal index");
        }

        if (getCapacity() == size) {
            resize(2 * getCapacity());
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 向数组末尾添加元素
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 向数组前端添加元素
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 查询数组指定位置元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (0 > index || size <= index) {
            throw new IllegalArgumentException("base get failed: illegal index");
        }
        return data[index];
    }

    /**
     * 数组指定位置元素赋值
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (0 > index || size <= index) {
            throw new IllegalArgumentException("base get failed: illegal index");
        }
        data[index] = e;
    }

    /**
     * 查询数组是否包含元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询元素在数组中的索引，不存在元素返回-1
     *
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定索引位置元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (0 > index || size <= index) {
            throw new IllegalArgumentException("base get failed: illegal index");
        }

        E e = data[index];
        for (int i = index; i <= size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;

        // 实际容量为原来的 1/4，缩容到原来的一半
        if (size == data.length / 4 && 0 != data.length) {
            resize(data.length / 2);
        }
        return e;
    }

    /**
     * 删除首个元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除末尾元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除某个元素
     *
     * @param e
     * @return
     */
    public boolean removeElement(E e) {
        int index = find(e);
        if (-1 != index) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 扩容/缩容数组
     *
     * @param newCapacity
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 交换数组元素
     *
     * @param i1
     * @param i2
     */
    public void swap(int i1, int i2) {
        if (i1 >= data.length || i2 >= data.length) {
            throw new IllegalArgumentException("invalid index");
        }
        E v1 = data[i1];
        E v2 = data[i2];
        data[i1] = v2;
        data[i2] = v1;
    }
}
