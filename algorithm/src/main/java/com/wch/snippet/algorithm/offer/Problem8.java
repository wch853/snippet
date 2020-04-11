package com.wch.snippet.algorithm.offer;

/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 *
 * @author wch
 */
public class Problem8 {

    private static class TreeNode {

        private int value;

        private TreeNode left;

        private TreeNode right;

        /**
         * 指向父节点
         */
        private TreeNode parent;

        private TreeNode(int value) {
            this.value = value;
        }
    }

    /**
     * <pre>
     *        1
     *      /  \
     *    2     3
     *   /     / \
     * 4      5   6
     *  \        /
     *   7      8
     * </pre>
     * 寻找中序遍历的下一个节点
     * （1）如果当前节点有右子树，则下一个节点是右子树的最左节点
     * （2）如果当前节点没有右子树，则下一个节点是向父节点遍历，指向左子树的节点
     */
    private static TreeNode solution(TreeNode node) {
        if (null != node.right) {
            TreeNode child = node.right;
            while (null != child.left) {
                child = child.left;
            }
            return child;
        }

        while (null != node.parent) {
            TreeNode parent = node.parent;
            if (node == parent.left) {
                // 父节点的左子树为当前子树
                return parent;
            }
            node = parent;
        }
        return null;
    }
}
