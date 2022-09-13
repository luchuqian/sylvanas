package com.sylvanas.leetcode.medium;

import java.util.*;

/**
 * https://leetcode.cn/problems/subsets-ii/
 */
public class SubSetWithDup {

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        LinkedList<Integer> trackList = new LinkedList<>();
        Arrays.sort(nums);
        backtrack(resultList, trackList, nums, 0);
        return resultList;
    }

    public static void backtrack(List<List<Integer>> resultList,
                                 LinkedList<Integer> trackList,
                                 int[] nums,
                                 int start) {
        resultList.add(new LinkedList<>(trackList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            trackList.addLast(nums[i]);
            backtrack(resultList, trackList, nums, i + 1);
            trackList.removeLast();
        }
    }


}
