package com.sylvanas.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/invert-binary-tree/?envType=study-plan-v2&envId=top-100-liked
 */
public class InvertTree {


  /**
   * 递归遍历(DFS)
   *
   * @param root
   * @return
   */
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return root;
    }

    TreeNode left = root.left;
    root.left = invertTree(root.right);
    root.right = invertTree(left);
    return root;
  }


  public TreeNode invertTree2(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode left = invertTree2(root.left);
    TreeNode right = invertTree2(root.right);
    root.left = right;
    root.right = left;

    return root;
  }


  public TreeNode invertByBFS(TreeNode root) {
    if (root == null) {
      return root;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      TreeNode curNode = queue.poll();
      TreeNode leftNode = curNode.left;
      curNode.left = curNode.right;
      curNode.right = leftNode;
      if (curNode.left != null) {
        queue.offer(curNode.left);
      }
      if (curNode.right != null) {
        queue.offer(curNode.right);
      }
    }
    return root;
  }


  public TreeNode invertByStack(TreeNode root) {
    if (root == null) {
      return null;
    }
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode cur = stack.pop();
      if (cur != null) {
        stack.push(cur.right);
        stack.push(cur.left);
        TreeNode left = cur.left;
        cur.left = cur.right;
        cur.right = left;
      }
    }
    return root;
  }

}
