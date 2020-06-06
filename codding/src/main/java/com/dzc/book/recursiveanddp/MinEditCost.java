package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-06 13:41
 * <p>
 * 最小编辑代价
 * <p>
 * 给定两个字符串str1, str2, 给定三个整数 ic, dc, rc 分别代表插入 删除和 替换 一个字符的代价，
 * <p>
 * 返回将str1 编辑成 str2 的最小代价
 */
public class MinEditCost {

    public int minEditCost(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return -1;
        }
        if (str1.equals(str2)) {
            return 0;
        }

        // dp[][]
        // dp[i][j] 代表 该位置. str1[0..i-1] 编辑成 str2[0..j-1] 字符的最小代价

        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;

        for (int i = 1; i <= m; i++) {
            dp[0][i] = ic * i;
        }

        for (int j = 1; j <= n; j++) {
            dp[j][0] = dc * j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1] + ic, dp[i - 1][j] + dc);
                if (str1.charAt(i) != str2.charAt(j)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + rc);
                } else {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}
