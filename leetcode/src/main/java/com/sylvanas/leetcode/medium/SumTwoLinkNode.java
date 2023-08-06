package com.sylvanas.leetcode.medium;



/**
 * https://leetcode.cn/problems/merge-two-sorted-lists/?envType=study-plan-v2&envId=top-100-liked
 */
public class SumTwoLinkNode {


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        int carry = 0;
        ListNode resultNode = new ListNode(0);
        ListNode resultHead = resultNode;
        while (list1 != null || list2 != null) {
            int val1 = list1 != null ? list1.val : 0;
            int val2 = list2 != null ? list2.val : 0;
            int result = val1 + val2 + resultNode.val;
            if (result >= 10) {
                result = result - 10;
                carry = 1;
            } else {
                carry = 0;
            }
            resultNode.val = result;
            if (list1 != null || list2 != null || carry > 0) {
                resultNode.next = new ListNode(carry);
                resultNode = resultNode.next;
            }
            list1 = list1 != null ? list1.next : null;
            list2 = list2 != null ? list2.next : null;
        }
        return resultHead;
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
