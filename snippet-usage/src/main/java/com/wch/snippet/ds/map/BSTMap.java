package com.wch.snippet.ds.map;

/**
 * 基于二分搜索树实现Map
 *
 * @param <K>
 * @param <V>
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    /**
     * 根节点
     */
    private Node root;

    /**
     * 节点数量
     */
    private int size;

    private class Node {

        public K key;

        public V value;

        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * 添加元素
     *
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (null == node) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    /**
     * 查找指定元素所在节点
     *
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node, K key) {
        if (null != node) {
            if (key.compareTo(node.key) == 0) {
                return node;
            } else if (key.compareTo(node.key) < 0) {
                return getNode(node.left, key);
            } else {
                return getNode(node.right, key);
            }
        }
        return null;
    }

    /**
     * 删除指定key对应的节点
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        Node remove = remove(root, key);
        return null == remove ? null : remove.value;
    }

    private Node remove(Node node, K key) {
        if (null != node) {
            if (key.compareTo(node.key) < 0) {
                node.left = remove(node.left, key);
            } else if (key.compareTo(node.key) > 0) {
                node.right = remove(node.right, key);
            } else {
                if (null == node.left) {
                    // 左子树为空，使用右子树替代当前子树
                    Node rightNode = node.right;
                    node.right = null;
                    size--;
                    return rightNode;
                } else if (null == node.right) {
                    // 右子树为空，使用左子树替代当前子树
                    Node leftNode = node.left;
                    node.left = null;
                    size--;
                    return leftNode;
                } else {
                    Node successor = minimum(node.right);
                    successor.right = removeMin(node.right);
                    successor.left = node.left;
                    node.left = node.right = null;
                    return successor;
                } 
            }
        }
        return null;
    }

    /**
     * 返回当前节点删除了最小值所在节点的子树
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        // 当前节点替换成的节点
        Node replaceNode;
        if (null == node.left) {
            // 此节点为需要删除的节点
            // 原来的右子树
            Node rightNode = node.right;
            node.right = null;
            // 替换原有节点
            replaceNode = rightNode;
            size--;
        } else {
            // 左子树替换
            node.left = removeMin(node.left);
            replaceNode = node;
        }
        return replaceNode;
    }

    /**
     * 查询最小元素所在节点
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if (null == node.left) {
            return node;
        }
        return minimum(node.left);
    }

    @Override
    public boolean contains(K key) {
        return null != getNode(root, key);
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return null == node ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (null == node) {
            throw new IllegalArgumentException("set failed, key not exist");
        }
        node.value = value;
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
