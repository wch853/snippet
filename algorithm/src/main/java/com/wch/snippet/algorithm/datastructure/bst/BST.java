package com.wch.snippet.algorithm.datastructure.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
 * Binary Search Tree
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> {

    private class Node<E> {

        public E e;

        public Node<E> left, right;

        public Node(E e) {
            this.e = e;
        }
    }

    private Node<E> root;

    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 查询树节点数
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 查询是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加元素
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 递归插入元素
     *
     * @param node
     * @param e
     * @return
     */
    private Node<E> add(Node<E> node, E e) {
        if (null == node) {
            node = new Node<>(e);
            size++;
        } else if (node.e.compareTo(e) > 0) {
            node.left = add(node.left, e);
        } else if (node.e.compareTo(e) < 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node<E> node, E e) {
        boolean isFind = false;
        if (null != node) {
            if (node.e.compareTo(e) == 0) {
                isFind = true;
            } else if (node.e.compareTo(e) > 0) {
                isFind = contains(node.left, e);
            } else {
                isFind = contains(node.right, e);
            }
        }
        return isFind;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<E> node) {
        if (null != node) {
            System.out.println(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<E> node) {
        if (null != node) {
            inOrder(node.left);
            System.out.println(node.e);
            inOrder(node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<E> node) {
        if (null != node) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.e);
        }
    }

    /**
     * 基于栈的非递归前序遍历实现
     */
    public void preOrderByStack() {
        if (null != root) {
            Stack<Node<E>> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node<E> pop = stack.pop();
                System.out.println(pop.e);
                if (null != pop.left) {
                    stack.push(pop.left);
                }
                if (null != pop.right) {
                    stack.push(pop.right);
                }
            }
        }
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        if (null != root) {
            queue.add(root);
            while (!queue.isEmpty()) {
                Node cur = queue.remove();
                System.out.println(cur.e);

                if (null != cur.left) {
                    queue.add(cur.left);
                }
                if (null != cur.right) {
                    queue.add(cur.right);
                }
            }
        }
    }

    /**
     * 查询最小值所在节点
     *
     * @return
     */
    public E minimum() {
        if (0 == size) {
            throw new IllegalArgumentException("BST is empty");
        }

        return minimum(root).e;
    }

    private Node<E> minimum(Node<E> node) {
        Node<E> minimum;
        if (null == node.left) {
            minimum = node;
        } else {
            minimum = minimum(node.left);
        }
        return minimum;
    }

    /**
     * 查询最大值所在节点
     *
     * @return
     */
    public E maximum() {
        if (0 == size) {
            throw new IllegalArgumentException("BST is empty");
        }

        return maximum(root).e;
    }

    private Node<E> maximum(Node<E> node) {
        Node<E> maximum;
        if (null == node.right) {
            maximum = node;
        } else {
            maximum = maximum(node.right);
        }
        return maximum;
    }

    /**
     * 删除最小值所在节点
     *
     * @return
     */
    public E removeMin() {
        E minimum = minimum();
        removeMin(root);
        return minimum;
    }

    /**
     * 返回当前节点删除了最小值所在节点的子树
     *
     * @param node
     * @return
     */
    private Node<E> removeMin(Node<E> node) {
        // 当前节点替换成的节点
        Node<E> replaceNode;
        if (null == node.left) {
            // 此节点为需要删除的节点
            // 原来的右子树
            Node<E> rightNode = node.right;
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
     * 删除最大值所在节点
     *
     * @return
     */
    public E removeMax() {
        E maximum = maximum();
        removeMax(root);
        return maximum;
    }

    /**
     * 返回当前节点删除了最大值所在节点的子树
     *
     * @param node
     * @return
     */
    public Node<E> removeMax(Node<E> node) {
        // 当前节点替换成的节点
        Node<E> replaceNode;
        if (null == node.right) {
            // 此节点为需要删除的节点
            Node<E> leftNode = node.left;
            node.left = null;
            replaceNode = leftNode;
            size--;
        } else {
            node.right = removeMax(node.right);
            replaceNode = node;
        }
        return replaceNode;
    }

    /**
     * 删除指定元素所在节点
     *
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 返回删除了等于指定元素的节点的子树
     *
     * @param e
     * @return
     */
    private Node<E> remove(Node<E> node, E e) {
        Node<E> replaceNode = null;
        if (null != node) {
            if (node.e.compareTo(e) > 0) {
                node.left = remove(node.left, e);
                replaceNode = node;
            } else if (node.e.compareTo(e) < 0) {
                node.right = remove(node.right, e);
                replaceNode = node;
            } else {
                // 删除节点
                if (null == node.left) {
                    // 左子树为空，使用右子树代替当前节点
                    Node<E> rightNode = node.right;
                    node.right = null;
                    size--;
                    replaceNode = rightNode;
                } else if (null == node.right) {
                    Node<E> leftNode = node.left;
                    node.left = null;
                    size--;
                    replaceNode = leftNode;
                } else {
                    // 左右子树都不为空，查找右子树最小的节点，代替当前节点
                    Node<E> successor = minimum(node.right);
                    successor.right = removeMin(node.right);
                    successor.left = node.left;

                    node.left = node.right = null;
                    replaceNode = successor;
                }
            }
        }
        return replaceNode;
    }
}
