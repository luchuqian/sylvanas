package com.sylvanas.leetcode.medium;

/**
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/?envType=study-plan-v2&envId=top-100-liked
 */
public class FindKMinNumOfTree {
    int rank;
    int kMin;

    public int kthSmallest(TreeNode root, int k) {
        inorderTraverse(root, k);
        return kMin;
    }


    public void inorderTraverse(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        inorderTraverse(node.left, k);
        rank++;
        if (rank == k) {
            kMin = node.val;
        }
        inorderTraverse(node.right, k);
    }

}
