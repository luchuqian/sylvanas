package com.sylvanas.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * https://leetcode.cn/problems/permutations/
 */
public class Permutation {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> trackList = new LinkedList<>();
        backtrace(resultList, trackList, nums, used);
        return resultList;
    }

    public static void backtrace(List<List<Integer>> resultList, LinkedList<Integer> trackList, int[] nums, boolean[] used) {
        if (trackList.size() == nums.length) {
            resultList.add(new ArrayList<>(trackList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (used[i]) {
                // nums[i] 已经在 track 中，跳过
                continue;
            }
            trackList.add(nums[i]);
            used[i] = true;
            backtrace(resultList, trackList, nums, used);
            trackList.removeLast();
            used[i] = false;
        }
    }

}
