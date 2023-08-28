package com.sylvanas.leetcode.medium;

import java.util.*;

/**
 * https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked
 * <p>
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> numSet = new HashSet<>();
        for (int n : nums) {
            numSet.add(n);
        }
        Map<Integer, Integer> startToLengthMap = new HashMap<>();
        for (int n : nums) {
            if (numSet.contains(n - 1)) {
                continue;
            }
            int max = 1;
            while (numSet.contains(n + 1)) {
                n++;
                max++;
            }
            startToLengthMap.put(n, max);
        }
        return startToLengthMap.values()
                .stream()
                .max(Integer::compareTo)
                .get();
    }

    public static void main(String[] args) {

    }


}
