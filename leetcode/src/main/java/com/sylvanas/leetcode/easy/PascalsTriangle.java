package com.sylvanas.leetcode.easy;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/pascals-triangle/?envType=study-plan-v2&envId=top-100-liked
 */
public class PascalsTriangle {


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows < 1) {
            return res;
        }
        // 先把第一层装进去作为 base case
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);
        // 开始一层一层生成，装入 res
        for (int i = 2; i <= numRows; i++) {
            List<Integer> prevRow = res.get(res.size() - 1);
            res.add(generateNextRow(prevRow));
        }
        return res;
    }

    // 输入上一层的元素，生成并返回下一层的元素
    List<Integer> generateNextRow(List<Integer> prevRow) {
        List<Integer> curRow = new ArrayList<>();
        curRow.add(1);
        for (int i = 0; i < prevRow.size() - 1; i++) {
            curRow.add(prevRow.get(i) + prevRow.get(i + 1));
        }
        curRow.add(1);
        return curRow;
    }

}
