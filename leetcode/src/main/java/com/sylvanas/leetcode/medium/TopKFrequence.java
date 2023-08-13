package com.sylvanas.leetcode.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/top-k-frequent-elements/?envType=study-plan-v2&envId=top-100-liked
 */
public class TopKFrequence {

    public int[] topKFrequent(int[] nums, int k) {
        // nums 中的元素 -> 该元素出现的频率
        Map<Integer, Integer> valToFreq = new HashMap<>();
        for (int v : nums) {
            valToFreq.put(v, valToFreq.getOrDefault(v, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparing(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : valToFreq.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                // 弹出最小元素，维护队列内是 k 个频率最大的元素
                pq.poll();
            }
        }

        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            // res 数组中存储前 k 个最大元素
            res[i] = pq.poll().getKey();
        }

        return res;
    }
}
