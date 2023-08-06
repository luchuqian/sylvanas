package com.sylvanas.leetcode.hard;

import com.sylvanas.leetcode.easy.IntersectionLinkNode;
import com.sylvanas.leetcode.medium.SumTwoLinkNode;
import com.sylvanas.link.algorithm.SingleLinkListNode;

/**
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/?envType=study-plan-v2&envId=top-100-liked
 */
public class ReverseLinkNodeInK {

    public ListNode reverseKGroup(ListNode head, int k) {
        return reverseKGroup(head, null, k);
    }

    public ListNode reverseKGroup(ListNode head, ListNode preNode, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int num = k;
        ListNode first = head;
        ListNode p = head;
        // 不足k个不翻转
        for (int i = 0; i < k; i++) {
            if (p == null) {
                return first;
            }
            p = p.next;
        }
        while (num > 0) {
            ListNode next = head.next;
            head.next = preNode;
            preNode = head;
            head = next;
            num--;
        }
        first.next = reverseKGroup(head, preNode, k);
        return preNode;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return this.val + "" + (next != null ? next.toString() : "");
        }
    }

}
