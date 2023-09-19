package com.sylvanas.leetcode.easy;


/**
 * https://leetcode.cn/problems/subtree-of-another-tree/
 */
public class SubTree {

  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (subRoot == null) {
      return true;
    }
    if (root == null) {
      return false;
    }
    // 后续遍历 在回到父节点时做等价树判断
    return isSubtree(root.left, subRoot)
        || isSubtree(root.right, subRoot)
        || isSameTree(root, subRoot);
  }


  public boolean isSameTree(TreeNode first, TreeNode second) {
    if (first == null && second == null) {
      return true;
    }

    if (first == null || second != null) {
      return false;
    }

    return first.val == second.val
        && isSameTree(first.left, second.left)
        && isSameTree(first.right, second.right);
  }


}
