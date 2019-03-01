package com.dzc.learn.letcode;


public class UniqueBinarySearchTrees {

    public int numTrees(int n) {
        int[] G = new int[n + 1];
        // n = 0 || n = 1 时， 只有一种可能的情况
        G[0] = G[1] = 1;

        // 从2开始的 每种情况
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    public static void main(String[] args) {
        UniqueBinarySearchTrees ub = new UniqueBinarySearchTrees();
        ub.numTrees(4);
    }
}
