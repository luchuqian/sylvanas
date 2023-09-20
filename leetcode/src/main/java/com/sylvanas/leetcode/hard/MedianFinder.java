package com.sylvanas.leetcode.hard;

import java.util.PriorityQueue;

public class MedianFinder {
    private PriorityQueue<Integer> ascHeap;
    private PriorityQueue<Integer> descHeap;

    public MedianFinder() {
        // 小顶堆
        ascHeap = new PriorityQueue<>();
        // 大顶堆
        descHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public double findMedian() {
        // 如果元素不一样多，多的那个堆的堆顶元素就是中位数
        if (ascHeap.size() < descHeap.size()) {
            return descHeap.peek();
        } else if (ascHeap.size() > descHeap.size()) {
            return ascHeap.peek();
        }
        // 如果元素一样多，两个堆堆顶元素的平均数是中位数
        return (ascHeap.peek() + descHeap.peek()) / 2.0;


    }

    public void addNum(int num) {
        // 控制两堆的与元素差不超过1 同时每个元素都需要在两个堆之间走一遍
        if (descHeap.size() >= ascHeap.size()) {
            descHeap.offer(num);
            ascHeap.offer(descHeap.poll());
        } else {
            ascHeap.offer(num);
            descHeap.offer(ascHeap.poll());
        }
    }

}
