package com.sylvanas.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NSum {

  public static List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
    int sz = nums.length;
    List<List<Integer>> resultList = new ArrayList<>();
    // 至少是 2Sum，且数组大小不应该小于 n
    if (n < 2 || sz < n) return resultList;
    Arrays.sort(nums);
    // 2Sum 是 base case
    if (n == 2) {
      // 双指针那一套操作
      int lo = start, hi = sz - 1;
      while (lo < hi) {
        int sum = nums[lo] + nums[hi];
        int left = nums[lo], right = nums[hi];
        if (sum < target) {
          while (lo < hi && nums[lo] == left) lo++;
        } else if (sum > target) {
          while (lo < hi && nums[hi] == right) hi--;
        } else {
          resultList.add(Arrays.asList(left, right));
          while (lo < hi && nums[lo] == left) lo++;
          while (lo < hi && nums[hi] == right) hi--;
        }
      }
    } else {
      // n > 2 时，递归计算 (n-1)Sum 的结果
      for (int i = start; i < sz; i++) {
        List<List<Integer>> subResultList = nSum(nums, n - 1, i + 1, target - nums[i]);
        for (List<Integer> sub : subResultList) {
          // (n-1)Sum 加上 nums[i] 就是 nSum
          sub.add(nums[i]);
          resultList.add(sub);
        }
        while (i < sz - 1 && nums[i] == nums[i + 1]) i++;
      }
    }
    return resultList;
  }

  public static void main(String[] args) {
    int[] arrays = new int[]{2, 7, 1, 15};
    int target = 9;
    List<List<Integer>> resultList = nSum(arrays, 2, 0, target);
    resultList.forEach(list -> {
      StringBuffer sb = new StringBuffer();
      list.forEach(num -> sb.append(num).append(" "));
      System.out.println(sb.toString().trim());
    });
  }

}
