package com.sylvanas.tree.iterator;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class LevelTraversal {

    public static List<List<Integer>> levelOrderTraversal(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        List<List<Integer>> resultList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            while (size-- > 0) {
                TreeNode currentNode = queue.poll();
                if (currentNode != null) {
                    levelList.add(currentNode.val);
                    queue.offer(currentNode.left);
                    queue.offer(currentNode.right);
                }
            }
            if (!CollectionUtils.isEmpty(levelList)) {
                resultList.add(levelList);
            }
        }
        return resultList;
    }

    public static List<List<Integer>> levelOrderTraversalInRecursion(TreeNode node) {
        List<List<Integer>> resultList = new ArrayList<>();
        levelInRecursion(resultList, node, 0);
        return resultList;
    }

    public static void levelInRecursion(List<List<Integer>> resultList, TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (resultList.size() <= level) {
            resultList.add(new LinkedList<>());
        }
        resultList.get(level).add(node.val);
        levelInRecursion(resultList, node.left, level + 1);
        levelInRecursion(resultList, node.right, level + 1);
    }


    public static List<List<Integer>> exercise(TreeNode node) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> resultList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            while (size-- > 0) {
                TreeNode curNode = queue.poll();
                if (curNode != null) {
                    levelList.add(curNode.val);
                    queue.offer(curNode.left);
                    queue.offer(curNode.right);
                }
            }
            resultList.add(levelList);
        }
        return resultList;
    }

    public static List<List<Integer>> exerciseInRecur(TreeNode node) {
        List<List<Integer>> resultList = new ArrayList<>();
        recur(node, resultList, 0);
        return resultList;
    }

    public static void recur(TreeNode node, List<List<Integer>> resultList, int level) {
        if (node == null) {
            return;
        }
        // if not create levelList, create it
        if (resultList.size() <= level) {
            resultList.add(new ArrayList<>());
        }
        resultList.get(level).add(node.val);
        recur(node.left, resultList, level + 1);
        recur(node.right, resultList, level + 1);
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
        List<List<Integer>> resultList = exerciseInRecur(node);
        resultList.forEach(list -> {
            list.forEach(ele -> System.out.print(ele + " "));
            System.out.println();
        });
    }

}
