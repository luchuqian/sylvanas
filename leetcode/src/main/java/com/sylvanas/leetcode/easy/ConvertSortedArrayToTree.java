package com.sylvanas.leetcode.easy;

/**
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/solutions/312607/jiang-you-xu-shu-zu-zhuan-huan-wei-er-cha-sou-s-33/?envType=study-plan-v2&envId=top-100-liked
 */
public class ConvertSortedArrayToTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return convert(nums, 0, nums.length);
    }


    public TreeNode convert(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (right + left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = convert(nums, left, mid - 1);
        root.right = convert(nums, mid + 1, right);
        return root;
    }


}
