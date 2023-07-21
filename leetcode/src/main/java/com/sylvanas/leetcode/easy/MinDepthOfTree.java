package com.sylvanas.leetcode.easy;

import com.sylvanas.tree.iterator.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MinDepthOfTree {

  public int minDepth(TreeNode root) {
    return bfs(root);
  }


  public int bfs(TreeNode treeNode) {
    if (treeNode == null) {
      return 0;
    }
    int depth = 0;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(treeNode);
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size > 0) {
        TreeNode cur = queue.poll();
        if (cur.left == null && cur.right == null) {
          return depth;
        }
        if (cur.left != null) {
          queue.offer(cur.left);
        }
        if (cur.right != null) {
          queue.offer(cur.right);
        }
        size--;
      }
      depth++;
    }
    return depth;
  }

}
