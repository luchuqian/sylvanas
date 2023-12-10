package com.sylvanas.sort.algorithm;

public class SingleLinkMeagerSort {

    public static Node sort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node middleNode = findMiddleNode(head);
        Node rightHead = middleNode.next;
        middleNode.next = null;

        Node leftAfterSort = sort(head);
        Node rightAfterSort = sort(rightHead);
        return mergeNode(leftAfterSort, rightAfterSort);
    }

    private static Node mergeNode(Node left, Node right) {
        Node head = new Node(null, -1);
        Node result = head;
        while (left != null && right != null) {
            if (left.val < right.val) {
                head.next = left;
                left = left.next;
            } else {
                head.next = right;
                right = right.next;
            }
            head = head.next;
        }

        head.next = left != null ? left : right;
        return result.next;
    }


    public static Node findMiddleNode(Node head) {
        Node slow = head;
        Node fast = slow.next;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        Node head = new Node(null, -1);
        head.next = new Node(new Node(new Node(new Node(new Node(null, 4), 10), 2), 3), 1);
        System.out.println(exerciseSort(head.next));
    }

    public static Node exerciseSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node leftHead = head;
        Node middleNode = exerciseFindMiddle(leftHead);
        Node rightHead = middleNode.next;
        middleNode.next = null;
        exerciseSort(leftHead);
        exerciseSort(rightHead);
        exerciseMerge(leftHead, rightHead);
        return head;
    }


    public static Node exerciseFindMiddle(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (slow != null && fast != null && head.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node exerciseMerge(Node leftHead, Node rightHead) {
        if (leftHead == null) {
            return rightHead;
        }
        if (rightHead == null) {
            return leftHead;
        }
        Node head = new Node(-1);
        Node result = head;
        while (leftHead != null && rightHead != null) {
            int leftVal = leftHead.val;
            int rightVal = rightHead.val;
            if (leftVal < rightVal) {
                head.next = leftHead;
                leftHead = leftHead.next;
            } else {
                head.next = rightHead;
                rightHead = rightHead.next;
            }
            head = head.next;
        }
        head.next = leftHead != null ? leftHead : rightHead;
        return result.next;
    }
}

class Node {
    public Node next;
    public int val;

    public Node(int val) {
        this.val = val;
    }

    public Node(Node next, int val) {
        this.next = next;
        this.val = val;
    }

    @Override
    public String toString() {
        return this.val + "->" + (this.next != null ? this.next.toString() : "null");
    }
}
