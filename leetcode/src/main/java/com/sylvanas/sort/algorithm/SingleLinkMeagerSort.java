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
        Node middle = exerciseFindMiddle(head);
        Node rightHead = middle.next;
        middle.next = null;
        Node leftHead = head;
        Node leftAfterSort = exerciseSort(leftHead);
        Node rightAfterSort = exerciseSort(rightHead);
        return exerciseMerge(leftAfterSort, rightAfterSort);
    }


    public static Node exerciseFindMiddle(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = slow.next;
        while (slow != null && fast != null && fast.next != null) {
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
        Node resultNode = new Node(null, 0);
        Node resultHead = resultNode;
        while (leftHead != null && rightHead != null) {
            int leftVal = leftHead.val;
            int rightVal = rightHead.val;
            if (leftVal > rightVal) {
                resultNode.next = new Node(null, leftVal);
                leftHead = leftHead.next;
            } else {
                resultNode.next = new Node(null, rightVal);
                rightHead = rightHead.next;
            }
            resultNode = resultNode.next;
        }
        resultNode.next = leftHead == null ? rightHead : leftHead;
        return resultHead.next;
    }
}

class Node {
    public Node next;
    public int val;

    public Node(Node next, int val) {
        this.next = next;
        this.val = val;
    }

    @Override
    public String toString() {
        return this.val + "->" + (this.next != null ? this.next.toString() : "null");
    }
}
