package com.dzc.book.string;

/**
 * @author Administrator
 * @date 2020-06-17 19:54
 * <p>
 * 添加最少的字符使字符串整体都是回文字符串
 */
public class Palindrome {

    /**
     * 用动态规划找到当前字符串 转换为 回文字符串所需要的字符
     *
     * @param str
     * @return
     */
    public int[][] getDp(char[] str) {

        int[][] dp = new int[str.length][str.length];
        // dp i .. j 表示 str i .. j 变成回文字符串所需要的字符数
        for (int j = 1; j < str.length; j++) {
            dp[j][j - 1] = str[j] == str[j - 1] ? 0 : 1;
            for (int i = j - 2; i > -1; i--) {
                if (str[i] == str[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp;
    }


    public String getPalindrome(String str) {
        if (str == null || str.equals("")) {
            return null;
        }

        char[] chas = str.toCharArray();
        int[][] dp = getDp(chas);

        // 设置一个返回值的大小.
        char[] res = new char[chas.length + dp[0][chas.length - 1]];


        int i = 0;
        int j = chas.length - 1;

        int resl = 0;
        int resr = res.length - 1;

        while (i <= j) {
            if (chas[i] == chas[j]) {
                res[resl++] = chas[i++];
                res[resr--] = chas[j--];
            } else if (dp[i + 1][j] > dp[i][j - 1]) {
                // 当初就是用小的那个补.
                res[resl++] = chas[j];
                res[resr--] = chas[j--];
            } else {
                res[resl++] = chas[i];
                res[resr--] = chas[i++];
            }
        }

        return String.valueOf(res);
    }
}
