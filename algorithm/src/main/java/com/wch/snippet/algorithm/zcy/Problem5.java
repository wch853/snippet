package com.wch.snippet.algorithm.zcy;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * 用一个栈实现另一个栈的排序
 *
 * @author wch
 */
public class Problem5 {

    /**
     * 栈顶到栈底从大到小排序
     */
    private static Stack<Integer> stack = new Stack<>();

    /**
     * 希望辅助栈是栈顶到栈底从小到大排序，即大的元素放到栈底
     */
    private static Stack<Integer> help = new Stack<>();

    private static void sort() {
        while (!stack.isEmpty()) {
            // 从原始栈中取出栈顶元素
            Integer cur = stack.pop();
            if (help.isEmpty()) {
                // 辅助栈为空，放入元素
                help.push(cur);
            } else {
                int popSize = 0;
                boolean bottom = true;
                while (!help.isEmpty()) {
                    // 辅助栈不为空，查看栈顶元素
                    Integer top = help.peek();
                    if (cur > top) {
                        // 弹出元素大于辅助栈栈顶元素，需要往栈底放，先把辅助栈栈顶元素放回原始栈
                        stack.push(help.pop());
                        // 记录之后需要从原始栈中拿回的元素数量
                        popSize++;
                    } else {
                        // 弹出元素小于辅助栈栈顶元素，放入元素
                        help.push(cur);
                        // 从原始栈中拿回元素
                        for (; popSize > 0; popSize--) {
                            help.push(stack.pop());
                        }
                        bottom = false;
                        break;
                    }
                }
                if (bottom) {
                    // 如果弹出元素比辅助栈中所有元素都小
                    help.push(cur);
                    for (; popSize > 0; popSize--) {
                        help.push(stack.pop());
                    }
                }
            }
        }

        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            stack.push(random.nextInt(100));
        }
        printStack();

        sort();

        printStack();
    }

    private static void printStack() {
        System.out.println(Arrays.toString(stack.toArray()));
    }
}
