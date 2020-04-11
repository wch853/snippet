package com.wch.snippet.algorithm.offer;

/**
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * <p>
 * Input: {2, 3, 1, 0, 2, 5}, Output: 2
 *
 * @author wch
 */
public class Problem3 {

    /**
     * 利用一维数组下标连续的特性（原始数组）
     * // 创建一个n长度的数组，将原数组的元素拷贝到新数组对应的位置
     */
    private static boolean solution(int[] nums, int[] duplication) {
        if (null == nums || nums.length == 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            // 期望排在前面元素与下标对应
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    // 存在重复数字
                    duplication[0] = nums[i];
                    return true;
                }

                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 0, 2, 5};
        int[] duplication = new int[1];
        boolean found = solution(nums, duplication);
        if (found) {
            System.out.println(duplication[0]);
        }
    }
}
