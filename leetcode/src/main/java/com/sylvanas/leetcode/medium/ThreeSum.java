package com.sylvanas.leetcode.medium;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/
 */
public class ThreeSum {


  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      int target = 0 - nums[i];
      int left = i + 1;
      int right = nums.length - 1;
      if (i >= 1 && nums[i] == nums[i - 1]) {
        continue;
      }
      while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum > target) {
          int rightEndpoint = nums[right];
          while (left < right && nums[right] == rightEndpoint) {
            right--;
          }
        } else if (sum < target) {
          int leftEndpoint = nums[left];
          while (left < right && nums[left] == leftEndpoint) {
            left++;
          }
        } else {
          result.add(Arrays.asList(nums[i], nums[left], nums[right]));
          int rightEndpoint = nums[right];
          while (left < right && nums[right] == rightEndpoint) {
            right--;
          }
          int leftEndpoint = nums[left];
          while (left < right && nums[left] == leftEndpoint) {
            left++;
          }
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    List<List<Integer>> resultList = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
    resultList.forEach(list -> {
      StringBuffer sb = new StringBuffer();
      list.forEach(num -> sb.append(num).append(" "));
      System.out.println(sb.toString().trim());
    });
  }

}
