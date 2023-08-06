package com.sylvanas.leetcode.easy;

import com.sylvanas.leetcode.easy.IntersectionLinkNode.ListNode;

public class MergeAscLinkNode {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head = new ListNode(-1);
        ListNode result = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                result.next = list1;
                list1 = list1.next;
            } else {
                result.next = list2;
                list2 = list2.next;
            }
            result = result.next;
        }

        result.next = list1 == null ? list2 : list1;
        return head.next;
    }

}
