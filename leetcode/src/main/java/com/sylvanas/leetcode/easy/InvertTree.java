package com.sylvanas.leetcode.easy;

/**
 * https://leetcode.cn/problems/invert-binary-tree/?envType=study-plan-v2&envId=top-100-liked
 */
public class InvertTree {


    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode left = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }

}
