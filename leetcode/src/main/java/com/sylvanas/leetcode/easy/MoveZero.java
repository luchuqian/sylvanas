package com.sylvanas.leetcode.easy;

/**
 * https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class MoveZero {

    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 1) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int fast = i + 1;
                while (fast < nums.length) {
                    if (nums[fast] != 0) {
                        break;
                    }
                    fast++;
                }
                if (fast < nums.length) {
                    swap(nums, i, fast);
                }
            }
        }

    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {

    }

}
