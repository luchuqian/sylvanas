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
    System.out.println(sort(head.next));
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
