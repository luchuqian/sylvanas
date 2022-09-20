package com.sylvanas.link.algorithm;

public class SingleLinkListNode {
  public SingleLinkListNode next;
  public Integer val;


  public SingleLinkListNode(SingleLinkListNode next, Integer val) {
    this.next = next;
    this.val = val;
  }

  @Override
  public String toString() {
    return this.val + "->" + (this.next != null ? this.next.toString() : "null");
  }
}
