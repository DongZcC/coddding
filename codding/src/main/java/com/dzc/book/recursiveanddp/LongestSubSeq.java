package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-05 10:30
 * <p>
 * 最长递增子序列
 * <p>
 * 给定数组arr， 返回arr的最长递增子序列
 * <p>
 * 这个题目直接用dfs 去递归暴力求解， 应该是可以做出来的
 * <p>
 * 但是这个复杂度明显不符合要求
 */
public class LongestSubSeq {

    /**
     * 用dp 去做，
     * <p>
     * dp[N] 的数组， dp[i] 表示以arr[i]结尾的情况下， arr[0..i]中最大递增子序列的长度
     *
     * @param arr
     * @return
     */
    public int[] longestSubSeq(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getDp1(arr);
        return generate(arr, dp);
    }

    private int[] generate(int[] arr, int[] dp) {
        int len = 0;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }


        int[] res = new int[len];
        res[len--] = arr[index];

        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && (dp[i] == dp[index] - 1)) {
                res[len--] = arr[i];
                index = i;
            }
        }
        return res;
    }

    private int[] getDp1(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;

        for (int i = 1; i < arr.length; i++) {
            // 以i为结尾的情况下， 最大的递增子序列是 Max{ 0 .. i - 1} 下面最大的值   + 1
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

        }
        return dp;
    }


    /**
     * 利用二分查找进行优化 生成一个长度为N的数组 ends ends[0] = arr[0]
     * 生成整型变量right
     *
     * @param arr
     * @return
     */
    private int[] getDp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        dp[0] = 1;
        ends[0] = arr[0];
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l + 1;
        }
        return dp;
    }
}
