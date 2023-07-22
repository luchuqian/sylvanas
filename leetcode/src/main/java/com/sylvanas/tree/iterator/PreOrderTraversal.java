package com.sylvanas.tree.iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreOrderTraversal {

    public static void preorderTraversal(TreeNode node, List<Integer> resultList) {
        if (node == null) {
            return;
        }
        resultList.add(node.val);
        preorderTraversal(node.left, resultList);
        preorderTraversal(node.right, resultList);
    }

    public static void preorderTraversalInNoRecursion(TreeNode node, List<Integer> resultList) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            if (currentNode != null) {
                resultList.add(currentNode.val);
                stack.push(currentNode.right);
                stack.push(currentNode.left);
            }
        }
    }

    public static void exerciseRecur(TreeNode node, List<Integer> resultList) {
        if (node == null) {
            return;
        }
        resultList.add(node.val);
        exerciseRecur(node.left, resultList);
        exerciseRecur(node.right, resultList);
    }

    public static void exerciseWithoutRecur(TreeNode node, List<Integer> resultList) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode topNode = stack.pop();
            if (topNode != null) {
                resultList.add(topNode.val);
                stack.push(topNode.right);
                stack.push(topNode.left);
            }
        }

    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(
                new TreeNode(
                        new TreeNode(null, null, 50),
                        new TreeNode(null, null, 23),
                        20),
                new TreeNode(
                        new TreeNode(null, null, 11),
                        new TreeNode(null, null, 23),
                        77),
                100);
        List<Integer> resultList = new ArrayList<>();
        exerciseWithoutRecur(node, resultList);
        resultList.forEach(System.out::println);
    }

}

