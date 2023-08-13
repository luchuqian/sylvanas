package com.sylvanas.leetcode.medium;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/generate-parentheses/?envType=study-plan-v2&envId=top-100-liked
 */
public class GenerateParentheses {


    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        List<String> resultList = new ArrayList<>();
        StringBuilder track = new StringBuilder();
        int left = n;
        int right = n;
        backtrack(resultList, track, left, right);
        return resultList;
    }

    private void backtrack(List<String> resultList, StringBuilder track, int left, int right) {
        if (left > right) {
            return;
        }
        if (left < 0 || right < 0) {
            return;
        }
        if (left == 0 && right == 0) {
            resultList.add(track.toString());
            return;
        }
        track.append("(");
        backtrack(resultList, track, left - 1, right);
        track.deleteCharAt(track.length() - 1);


        track.append(")");
        backtrack(resultList, track, left, right - 1);
        track.deleteCharAt(track.length() - 1);

    }

}
