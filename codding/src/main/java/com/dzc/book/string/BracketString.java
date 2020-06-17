package com.dzc.book.string;

/**
 * @author Administrator
 * @date 2020-06-17 20:14
 * <p>
 * 判断是否括号字符串
 */
public class BracketString {

    /**
     * (())
     *
     * @param str
     * @return
     */
    public boolean isValid(String str) {

        char[] chas = str.toCharArray();

        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != '(' && chas[i] != ')') {
                return false;
            }
        }

        int leftCount = 0;
        int rightCount = 0;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] == '(') {
                leftCount++;
            }
            if (chas[i] == ')') {
                rightCount++;
            }
            if (rightCount > leftCount) {
                return false;
            }
        }
        return leftCount == rightCount;
    }


    // 补充问题， 给定一个括号字符串， 返回最长的有效括号长度
    public int validBracket(String str) {
        if (str == null || str.equals("")) {
            return -1;
        }
        char[] chas = str.toCharArray();
        // dp[i] 表示 str[0..i] 中必须以str[i]结尾的最长有效括号长度
        int[] dp = new int[chas.length];

        // dp[0] = 0
        int pre = 0;
        int res = 0;
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == '(') {
                dp[i] = 0;
            }
            if (chas[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chas[pre] == '(') {
                    // ()(()) 需要把这种情况也计算进来
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
                res = Math.max(res, dp[i]);
            }
        }

        return res;
    }


    public static void main(String[] args) {
        BracketString b = new BracketString();
        b.isValid("()a()");
    }


}
