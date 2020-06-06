package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-06 14:08
 * <p>
 * 字符串的交错组成
 * <p>
 * 给定三个字符串str1, str2, aim  如果aim包含且仅包含str1,str2的所有字符串。  并且各自字符串顺序不乱。 则称为交错组成
 */
public class ConsistString {

    public boolean consist(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null || str1.length() + str2.length() != aim.length()) {
            return false;
        }
        int m = str1.length();
        int n = str2.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 1; i <= m; i++) {
            dp[0][i] = false;
            if (str1.charAt(i) == aim.charAt(i)) {
                dp[0][i] = true;
            }
        }

        for (int j = 1; j <= n; j++) {
            dp[j][0] = false;
            if (str2.charAt(j) == aim.charAt(j)) {
                dp[j][0] = true;
            }
        }


        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] && str1.charAt(i) == aim.charAt(i + j - 1);
                if (dp[i][j - 1] && str2.charAt(j) == aim.charAt(i + j - 1)) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[m][n];
    }
}
