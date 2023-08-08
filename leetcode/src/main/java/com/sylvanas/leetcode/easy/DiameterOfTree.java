package com.sylvanas.leetcode.easy;

/**
 * https://leetcode.cn/problems/diameter-of-binary-tree/?envType=study-plan-v2&envId=top-100-liked
 */
public class DiameterOfTree {
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traverse(root);
        return maxDiameter;
    }


    public int traverse(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftMax = traverse(node.left);
        int rightMax = traverse(node.right);

        int curDiameter = leftMax + rightMax;
        maxDiameter = Math.max(curDiameter, maxDiameter);
        return Math.max(leftMax, rightMax) + 1;
    }


}
