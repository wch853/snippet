package com.wch.snippet.ds.segment;

/**
 * 线段树
 *
 * @param <E>
 */
public class SegmentTree<E extends Merger<E>> {

    private E[] data;

    private E[] tree;

    @SuppressWarnings("unchecked")
    public SegmentTree(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 在treeIndex位置创建区间[l, r]的线段树
     *
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[treeIndex];
            return;
        }

        int mid = l + (r - l) / 2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);
        buildSegmentTree(leftChild, l, mid);
        buildSegmentTree(rightChild, mid + 1, r);

        tree[treeIndex] = tree[leftChild].merge(tree[rightChild]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("get error, invalid index");
        }
        return data[index];
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 区间[l, r]的值
     *
     * @param l
     * @param r
     * @return
     */
    public E query(int l, int r) {
        if (l < 0 || l >= data.length || r < 0 || r >= data.length || l > r) {
            throw new IllegalArgumentException("invalid index");
        }

        return query(0, 0, data.length - 1, l, r);
    }

    /**
     * 从treeIndex查起，查询[rangeL, rangeR]范围内，[l, r]的值
     *
     * @param treeIndex
     * @param rangeL
     * @param rangeR
     * @param l
     * @param r
     * @return
     */
    private E query(int treeIndex, int rangeL, int rangeR, int l, int r) {
        int mid = l + (r - l) / 2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        if (l >= mid + 1) {
            return query(rightChild, mid + 1, data.length - 1, l, r);
        } else if (r <= mid) {
            return query(leftChild, 0, mid, l, r);
        }

        E leftResult = query(leftChild, rangeL, mid, l, mid);
        E rightResult = query(rightChild, mid + 1, rangeR, mid + 1, r);

        return leftResult.merge(rightResult);
    }

    /**
     * 更新index位置的元素
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("invalid index");
        }

        data[index] = e;


    }

    /**
     * 以treeIndex查起，更新[l, r]范围内index位置的元素
     *
     * @param treeIndex
     * @param l
     * @param r
     * @param index
     * @param e
     */
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        if (index >= mid + 1) {
            set(rightChild, mid + 1, r, index, e);
        } else {
            set(leftChild, l, mid, index, e);
        }

        tree[treeIndex] = tree[leftChild].merge(tree[rightChild]);
    }
}
