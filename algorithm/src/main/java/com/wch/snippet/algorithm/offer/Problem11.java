package com.wch.snippet.algorithm.offer;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * <p>
 * Input: {1, 2, 3, 4, 5}
 * Output: {3, 4, 5, 1, 2}
 *
 * @author wch
 */
public class Problem11 {

    /**
     * 通过二分法定位元素
     *
     * @return
     */
    private static int solution(int[] nums) {
        if (null == nums || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            // 划分为两个数组，期中一个递增，另一个包含最小元素
            if (nums[mid] <= nums[r]) {
                // 右数组递增，最小元素在左边
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println(solution(nums));
    }
}
