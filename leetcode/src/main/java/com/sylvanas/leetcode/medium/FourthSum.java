package com.sylvanas.leetcode.medium;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/
 */
public class FourthSum {


  public static List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i <= nums.length - 3; i++) {
      int threeSum = target - nums[i];
      if (i >= 1 && nums[i] == nums[i - 1]) {
        continue;
      }
      for (int j = i + 1; j <= nums.length - 2; j++) {
        int twoSum = threeSum - nums[j];
        if (j > i + 1 && nums[j] == nums[j - 1]) {
          continue;
        }
        int left = j + 1;
        int right = nums.length - 1;
        while (left < right) {
          int sum = nums[left] + nums[right];
          if (sum > twoSum) {
            int rightEndpoint = nums[right];
            while (left < right && nums[right] == rightEndpoint) {
              right--;
            }
          } else if (sum < twoSum) {
            int leftEndpoint = nums[left];
            while (left < right && nums[left] == leftEndpoint) {
              left++;
            }
          } else {
            result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
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

    }
    return result;
  }

  public static void main(String[] args) {
    List<List<Integer>> resultList = fourSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296);
    resultList.forEach(list -> {
      StringBuffer sb = new StringBuffer();
      list.forEach(num -> sb.append(num).append(" "));
      System.out.println(sb.toString().trim());
    });
  }

}
