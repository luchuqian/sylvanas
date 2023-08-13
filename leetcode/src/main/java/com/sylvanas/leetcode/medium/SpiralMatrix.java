package com.sylvanas.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.cn/problems/spiral-matrix/solutions/275393/luo-xuan-ju-zhen-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null) {
            return Collections.emptyList();
        }
        int count = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int total = row * col;

        int top = 0;
        int right = col - 1;
        int bottom = row - 1;
        int left = 0;
        List<Integer> resultList = new ArrayList<>();
        while (count < total) {
            for (int i = left; i <= right && count < total; i++) {
                resultList.add(matrix[top][i]);
                count++;
            }
            top++;
            for (int i = top; i <= bottom && count < total; i++) {
                resultList.add(matrix[i][right]);
                count++;
            }
            right--;
            for (int i = right; i >= left && count < total; i--) {
                resultList.add(matrix[bottom][i]);
                count++;
            }
            bottom--;
            for (int i = bottom; i >= top && count < total; i--) {
                resultList.add(matrix[i][left]);
                count++;
            }
            left++;
        }
        return resultList;
    }

}
