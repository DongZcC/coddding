package com.dzc.book.binarytree;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author Administrator
 * @date 2020-05-26 15:43
 * <p>
 * 在二叉树中找到累加和为指定值的最长路径长度
 */
public class SearchSumInBinaryTree {

    public int getMaxLength(Node head, int sum) {
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 0);
        return preOrder(head, sum, 0, 1, 0, sumMap);
    }

    private int preOrder(Node head, int sum, int preSum, int level, int maxLen, HashMap<Integer, Integer> sumMap) {
        if (head == null) {
            return maxLen;
        }

        int curSum = preSum + head.data;
        if (!sumMap.containsKey(curSum)) {
            sumMap.put(curSum, level);
        }
        if (sumMap.containsKey(curSum - sum)) {
            maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
        }
        maxLen = preOrder(head.left, sum, curSum, level + 1, maxLen, sumMap);
        maxLen = preOrder(head.right, sum, curSum, level + 1, maxLen, sumMap);
        if (level == sumMap.get(curSum)) {
            sumMap.remove(curSum);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Node root = new Node(-3);
        Node node3 = new Node(3);
        Node node1 = new Node(1);
        Node node0 = new Node(0);
        Node node01 = new Node(1);
        Node node6 = new Node(6);
        Node node99 = new Node(-9);
        Node node2 = new Node(2);
        Node node11 = new Node(1);


        root.left = node3;
        root.left = node99;
        node3.left = node1;
        node3.right = node0;
        node0.left = node01;
        node0.right = node6;
        node99.left = node2;
        node99.right = node11;

        SearchSumInBinaryTree search = new SearchSumInBinaryTree();
        search.getMaxLength(root, 6);
    }
}
