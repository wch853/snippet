package com.wch.snippet.algorithm.offer;

/**
 * 已知链表头结点和待删除节点指针，要求 O(1) 时间复杂度删除节点
 * @author wch
 */
public class Problem18 {

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
     * 分要删除节点是否为尾节点讨论
     * 平均时间复杂度为 (n + (n - 1)) / n = 2，复杂度O(1)
     */
    private Node solution(Node head, Node delete) {
        if (null == head || delete == head) {
            return null;
        }

        Node next = delete.next;
        if (null == next) {
            // 要删除的是尾节点，复杂度是 O(n)
            Node cur = head;
            while (cur.next != delete) {
                cur = cur.next;
            }
            head.next = null;
        } else {
            // 将要删除的节点的值替换为下一个节点的值
            delete.value = next.value;
            // 删除下一个节点，复杂度为 O(1)
            delete.next = next.next;
            next = null;
        }
        return head;
    }
}
