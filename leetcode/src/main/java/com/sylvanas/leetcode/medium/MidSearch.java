package com.sylvanas.leetcode.medium;

/**
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class MidSearch {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = nums.length - 1;

        int[] range = new int[]{-1, -1};
        range[0] = searchLeft(nums, target);
        range[1] = searchRight(nums, target);
        return range;
    }


    public static int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left < nums.length && nums[left] == target ? left : -1;
    }


    public static int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left - 1 >= 0 && nums[left - 1] == target ? left - 1 : -1;
    }


}
