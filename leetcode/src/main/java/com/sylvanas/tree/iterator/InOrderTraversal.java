package com.sylvanas.tree.iterator;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOrderTraversal {

    public static void inorderTraversal(TreeNode node, List<Integer> resultList) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, resultList);
        resultList.add(node.val);
        inorderTraversal(node.right, resultList);
    }

    public static void inorderTraversalByRecursion(TreeNode node, List<Integer> resultList) {
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            TreeNode currentNode = stack.pop();
            resultList.add(currentNode.val);
            node = currentNode.right;
        }
    }

    public static void exerciseInOrder(TreeNode treeNode, List<Integer> resultList) {
        if (treeNode == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            TreeNode curNode = stack.pop();
            resultList.add(curNode.val);
            treeNode = curNode.right;
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
        exerciseInOrder(node, resultList);
        resultList.forEach(System.out::println);
    }

}
