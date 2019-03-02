package com.wch.snippet.ds.unionFind;

public interface UnionFind {

    /**
     * 并查集中元素数量
     *
     * @return
     */
    int getSize();

    /**
     * 两个元素间是否连接
     *
     * @param p
     * @param q
     * @return
     */
    boolean isConnected(int p, int q);

    /**
     * 两个元素做并集操作
     *
     * @param p
     * @param q
     */
    void unionElements(int p, int q);
}
