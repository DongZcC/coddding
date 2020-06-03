package com.dzc.book.binarytree;

/**
 * @author Administrator
 * @date 2020-06-03 14:47
 * <p>
 * 统计和生成所有不同的二叉树
 * <p>
 * 给定一个整数N， 如果N < 1 代表空树结构， 否则代表中序遍历 {1，2，3...N} 请返回可能的二叉树结构
 * <p>
 * 动态规划来做。 算出每个节点的可能值
 */
public class GenAllDiffTree {


    public int numTrees(int n) {
        if (n < 2) {
            return 1;
        }

        int[] num = new int[n + 1];
        num[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                num[i] = num[j - 1] * num[i - j];
            }
        }
        return num[n];
    }
}
