package com.wch.snippet.algorithm.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 * Input:
 * pre: {1, 2, 4, 7, 3, 5, 6, 8}
 * in: {4, 7, 2, 1, 5, 3, 8, 6}
 * <p>
 * Output:
 * <pre>
 *        1
 *      /  \
 *    2     3
 *   /     / \
 * 4      5   6
 *  \        /
 *   7      8
 * </pre>
 *
 * @author wch
 */
public class Problem7 {

    private static class TreeNode {

        private int value;

        private TreeNode left;

        private TreeNode right;

        private TreeNode(int value) {
            this.value = value;
        }
    }

    /**
     * 记录中序遍历节点值和索引的关系
     */
    private static Map<Integer, Integer> map = new HashMap<>();

    private static TreeNode solution(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }

        return null;
    }

    /**
     * 根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * <p>
     * Input:
     * pre: {1, 2, 4, 7, 3, 5, 6, 8}
     * in: {4, 7, 2, 1, 5, 3, 8, 6}
     * <p>
     * Output:
     * <pre>
     *        1
     *      /  \
     *    2     3
     *   /     / \
     * 4      5   6
     *  \        /
     *   7      8
     * </pre>
     *
     * @author wch
     */
    private static TreeNode recursiveBuildTree(int[] pre, int[] in, int rootPreIndex, int inL, int inR) {
        // 根节点值
        int rootVal = pre[rootPreIndex];
        // 创建根节点
        TreeNode root = new TreeNode(rootVal);
        // root节点在中序遍历中的索引
        int rootPos = map.get(rootVal);
        // 左树节点数
        int leftTreeSize = rootPos - inL;
        // 使用
        root.left = recursiveBuildTree(pre, in, rootPreIndex + 1, inL, rootPos - 1);
        root.right = recursiveBuildTree(pre, in, rootPreIndex + leftTreeSize + 1, rootPos + 1, inR);
        return root;
    }
}
