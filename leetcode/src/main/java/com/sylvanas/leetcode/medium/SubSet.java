package com.sylvanas.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 找出一个数组里面的全部子集合
 */
public class SubSet {

  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> resultList = new ArrayList<>();
    LinkedList<Integer> trackList = new LinkedList<>();
    backtrack(resultList, trackList, nums, 0);
    return resultList;
  }

  public static void backtrack(List<List<Integer>> resultList, LinkedList<Integer> trackList, int[] nums, int start) {
    resultList.add(new LinkedList<>(trackList));
    for (int i = start; i < nums.length; i++) {
      trackList.add(nums[i]);
      backtrack(resultList, trackList, nums, i + 1);
      trackList.removeLast();
    }
  }

  public static void main(String[] args) {
    System.out.println(subsets(new int[]{1, 2, 3}));
  }


}
