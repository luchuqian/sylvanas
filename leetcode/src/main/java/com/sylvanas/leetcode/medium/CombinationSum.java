package com.sylvanas.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum-ii/
 */
public class CombinationSum {

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> resultList = new ArrayList<>();
    LinkedList<Integer> trackList = new LinkedList<>();
    int sum = 0;
    Arrays.sort(candidates);
    backtrack(resultList, trackList, candidates, 0, sum, target);
    return resultList;
  }

  public void backtrack(List<List<Integer>> resultList,
                        LinkedList<Integer> trackList,
                        int[] nums,
                        int start,
                        int sum,
                        int target) {
    if (sum == target) {
      resultList.add(new LinkedList<>(trackList));
    }
    if (sum > target) {
      return;
    }
    for (int i = start; i < nums.length; i++) {
      if (i > start && nums[i] == nums[i - 1]) {
        continue;
      }
      trackList.add(nums[i]);
      sum += nums[i];
      backtrack(resultList, trackList, nums, i + 1, sum, target);
      trackList.removeLast();
      sum -= nums[i];
    }
  }


}
