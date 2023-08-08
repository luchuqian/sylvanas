package com.sylvanas.leetcode.medium;


/**
 * https://leetcode.cn/problems/validate-binary-search-tree/?envType=study-plan-v2&envId=top-100-liked
 */
public class ValidTree {

    private long preNodeVal = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (!isValidBST(node.left)) {
            return false;
        }
        if (preNodeVal >= node.val) {
            return false;
        }
        preNodeVal = node.val;
        return isValidBST(node.right);
    }


}
