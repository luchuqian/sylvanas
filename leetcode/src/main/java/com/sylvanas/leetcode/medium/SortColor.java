package com.sylvanas.leetcode.medium;

import static com.sylvanas.sort.algorithm.BubbleSort.swap;

/**
 * https://leetcode.cn/problems/sort-colors/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class SortColor {

    public void sortColors(int[] nums) {
        int red = -1;
        int blue = nums.length;  //[0...zero]是0,[two...len-1]是2
        int white = 0;
        while (white < blue) {
            int tmp;
            if (nums[white] == 0) {
                red++;
                swap(nums, red, white);
                white++;
            } else if (nums[white] == 2) {
                blue--;
                swap(nums, red, white);
            } else {
                white++;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }




}
