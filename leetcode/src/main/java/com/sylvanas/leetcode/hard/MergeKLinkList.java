package com.sylvanas.leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://labuladong.github.io/algo/di-yi-zhan-da78c/shou-ba-sh-8f30d/shuang-zhi-0f7cc/
 */
public class MergeKLinkList {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode sentinelNode = new ListNode(-1);
        ListNode result = sentinelNode;
        //按值升序
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));

        for (ListNode head : lists) {
            if (head != null) {
                priorityQueue.offer(head);
            }
        }

        while (!priorityQueue.isEmpty()) {
            ListNode minNode = priorityQueue.poll();
            result.next = minNode;
            result = result.next;
            if (minNode.next != null) {
                priorityQueue.offer(minNode.next);
            }
        }

        return sentinelNode.next;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
