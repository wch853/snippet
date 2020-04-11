package com.wch.snippet.algorithm.datastructure.map;

public class LinkedMap<K, V> implements Map<K, V> {

    private class Node {

        public K key;

        public V value;

        private Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }
    }

    /**
     * 头结点
     */
    private Node dummy;

    /**
     * 元素数量
     */
    private int size;

    public LinkedMap() {
        this.dummy = new Node();
        this.size = 0;
    }

    /**
     * 根据key查询所在节点
     *
     * @param key
     * @return
     */
    private Node getNode(K key) {
        Node cur = dummy.next;
        while (null != cur) {
            if (key == cur.key) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (null == node) {
            dummy.next = new Node(key, value, dummy.next);
            size++;
        } else {
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node prev = dummy;
        while (null != prev.next) {
            if (prev.next.key.equals(key)) {
                break;
            }
            prev = prev.next;
        }

        if (null != prev.next) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return null != getNode(key);
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (null == node) {
            throw new IllegalArgumentException("set failed, key not exist");
        } else {
            node.value = value;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }
}
