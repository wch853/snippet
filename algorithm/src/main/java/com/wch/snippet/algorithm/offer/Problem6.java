package com.wch.snippet.algorithm.offer;


import java.util.Stack;

/**
 * 从后往前打印链表每个结点的值。
 * <p>
 * Input：1 -> 2 -> 3
 * <p>
 * Output: 3, 2, 1
 *
 * @author wch
 */
public class Problem6 {

    private static class Node {
        /**
         * 节点值
         */
        private int value;

        /**
         * 指向下一个节点的指针
         */
        private Node next;

        private Node() {

        }

        private Node(int value) {
            this.value = value;
        }
    }

    /**
     * 使用递归
     */
    private static void solution1(Node node) {
        if (null != node) {
            solution1(node.next);
            System.out.println(node.value);
        }
    }

    /**
     * 使用栈
     */
    private static void solution2(Node node) {
        Stack<Integer> stack = new Stack<>();
        while (null != node) {
            stack.push(node.value);
            node = node.next;
        }

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 头插法构建新的逆序链表
     */
    private static void solution3(Node node) {
        Node dummy = new Node();
        while (null != node) {
            // 创建新节点
            Node newNode = new Node(node.value);
            newNode.next = dummy.next;
            dummy.next = newNode;
            node = node.next;
        }

        Node valid = dummy.next;
        while (null != valid) {
            System.out.println(valid.value);
            valid = valid.next;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;

        solution1(node1);
        solution2(node1);
        solution3(node1);
    }
}
