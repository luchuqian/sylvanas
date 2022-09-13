package com.sylvanas.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combinations/
 */
public class Combine {
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> resultList = new ArrayList<>();
        LinkedList<Integer> trackList = new LinkedList<>();
        int[] nums = new int[k];
        for (int i = 0; i < k; i++) {
            nums[i] = i + 1;
        }
        backtrack(resultList, trackList, nums, 0, n);
        return resultList;
    }

    public static void backtrack(List<List<Integer>> resultList, LinkedList<Integer> trackList, int[] nums, int start, int target) {
        if (trackList.size() == target) {
            resultList.add(new LinkedList<>(trackList));
        }
        for (int i = start; i < nums.length; i++) {
            trackList.add(nums[i]);
            backtrack(resultList, trackList, nums, i + 1, target);
            trackList.removeLast();
        }
    }

    public static int sum(List<Integer> trackList) {
        int result = 0;
        for (Integer num : trackList) {
            result += num;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(combinationSum3(4, 2));
    }

}
