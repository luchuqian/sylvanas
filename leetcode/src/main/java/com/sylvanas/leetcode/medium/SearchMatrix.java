package com.sylvanas.leetcode.medium;

/**
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/solutions/1062538/sou-suo-er-wei-ju-zhen-ii-by-leetcode-so-9hcx/?envType=study-plan-v2&envId=top-100-liked
 */
public class SearchMatrix {


    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            int index = searchBin(matrix[i], 0, matrix[0].length - 1, target);
            if (index > -1) {
                return true;
            }
        }
        return false;
    }

    private int searchBin(int[] search, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;

        if (search[mid] == target) {
            return mid;
        } else if (search[mid] > target) {
            return searchBin(search, left, mid - 1, target);
        } else {
            return searchBin(search, mid + 1, right, target);
        }
    }

}
