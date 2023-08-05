package com.sylvanas.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NSum {

    public static List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
        int sz = nums.length;
        // 至少是 2Sum，且数组大小不应该小于 n
        if (n < 2 || sz < n) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
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

    public static List<List<Integer>> exerciseNSumInt(int[] nums, int n, int start, int target) {
        return exerciseNSum(nums, n, start, target);
    }


    public static List<List<Integer>> exerciseNSum(int[] nums, int n, int start, long target) {
        if (n < 2 || n > nums.length) {
            return Collections.emptyList();
        }
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);
        if (n == 2) {
            int low = start;
            int high = nums.length - 1;
            while (low < high) {
                int leftEndpoint = nums[low];
                int rightEndpoint = nums[high];
                int tupleSum = leftEndpoint + rightEndpoint;
                if (tupleSum < target) {
                    while (low < high && nums[low] == leftEndpoint) {
                        low++;
                    }
                } else if (tupleSum > target) {
                    while (low < high && nums[high] == rightEndpoint) {
                        high--;
                    }
                } else {
                    List<Integer> tupleList = new ArrayList<>();
                    tupleList.add(leftEndpoint);
                    tupleList.add(rightEndpoint);
                    resultList.add(tupleList);
                    while (low < high && nums[low] == leftEndpoint) {
                        low++;
                    }
                    while (low < high && nums[high] == rightEndpoint) {
                        high--;
                    }
                }
            }
        } else {
            for (int i = start; i < nums.length; i++) {
                long newTarget = (long) target;
                long cur = (long) nums[i];
                List<List<Integer>> subResultList = exerciseNSum(nums, n - 1, i + 1, newTarget - cur);
                for (List<Integer> sub : subResultList) {
                    sub.add(nums[i]);
                    resultList.add(sub);
                }
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{2, 7, 1, 15};
        int target = 9;
        List<List<Integer>> resultList = exerciseNSum(arrays, 2, 0, target);
        resultList.forEach(list -> {
            StringBuffer sb = new StringBuffer();
            list.forEach(num -> sb.append(num).append(" "));
            System.out.println(sb.toString().trim());
        });
    }

}
