package com.sylvanas.leetcode.medium;


import com.sylvanas.leetcode.medium.SumTwoLinkNode.ListNode;

/**
 * https://leetcode.cn/problems/swap-nodes-in-pairs/?envType=study-plan-v2&envId=top-100-liked
 */
public class ReverseLinkNodeInTwo {


    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = head.next;

        first.next = swapPairs(second.next);
        second.next = first;
        return second;
    }


}
