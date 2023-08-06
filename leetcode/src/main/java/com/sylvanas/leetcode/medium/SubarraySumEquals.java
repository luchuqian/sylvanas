package com.sylvanas.leetcode.medium;

import com.sylvanas.leetcode.hard.Queen;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/subarray-sum-equals-k/?envType=study-plan-v2&envId=top-100-liked
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
 */
public class SubarraySumEquals {


    public static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                int ele = nums[j];
                sum += ele;
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        subarraySum(new int[]{1, 2, 3}, 3);
    }

}
