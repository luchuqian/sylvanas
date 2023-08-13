package com.sylvanas.leetcode.medium;

import java.util.*;

/**
 * https://leetcode.cn/problems/merge-intervals/solutions/203562/he-bing-qu-jian-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked
 */
public class MergeRangeArray {


    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        LinkedList<int[]> result = new LinkedList<>();
        for (int[] ele : intervals) {
            int leftEndpoint = ele[0];
            int rightEndpoint = ele[1];
            if (result.isEmpty() || result.getLast()[1] < leftEndpoint) {
                result.addLast(ele);
            } else {
                result.getLast()[1] = Math.max(result.getLast()[1], rightEndpoint);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

}
