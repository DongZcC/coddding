package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-05 17:04
 * <p>
 * 最长公共子序列问题
 * <p>
 * 给定两个字符串Str1 和 str2 ， 返回两个字符串的最长公共子序列
 */
public class LongestPubSubSeq {

    /**
     * 返回公共子序列
     *
     * @param str1
     * @param str2
     * @return
     */
    public String longestPubSubSeq(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return null;
        }

        int[][] dp = getdp(str1.toCharArray(), str2.toCharArray());
        int m = str1.length();
        int n = str2.length();
        char[] res = new char[dp[m][n]];
        int index = res.length - 1;
        while (index >= 0) {
            if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                n--;
            } else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                m--;
            } else {
                res[index--] = str1.charAt(m);
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    public int[][] getdp(char[] str1, char[] str2) {
        int m = str1.length;
        int n = str2.length;
        int[][] dp = new int[m][n];

        // 做初始化操作..
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }

        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], str1[0] == str2[i] ? 1 : 0);
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }
}
