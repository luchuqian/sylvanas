package com.sylvanas.leetcode.medium;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations-ii/
 */
public class UniquePermit {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> trackList = new LinkedList<>();
        Arrays.sort(nums);
        backtrack(resultList, trackList, nums, used);
        return resultList;
    }

    public static void backtrack(List<List<Integer>> resultList, LinkedList<Integer> trackList, int[] nums, boolean[] used) {
        if (trackList.size() == nums.length) {
            resultList.add(new ArrayList<>(trackList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            trackList.add(nums[i]);
            used[i] = true;
            backtrack(resultList, trackList, nums, used);
            trackList.removeLast();
            used[i] = false;
        }
    }

}
