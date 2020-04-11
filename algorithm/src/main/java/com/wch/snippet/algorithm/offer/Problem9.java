package com.wch.snippet.algorithm.offer;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
 *
 * @author wch
 */
public class Problem9 {

    private Stack<Integer> in = new Stack<>();

    private Stack<Integer> out = new Stack<>();

    private void push(Integer i) {
        in.push(i);
    }

    private void pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                push(in.pop());
            }
        }

        if (out.isEmpty()) {
            throw new IllegalArgumentException("empty");
        }

        out.pop();
    }
}
