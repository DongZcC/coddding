package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-04 16:22
 * <p>
 * <p>
 * 换钱的方法数
 * <p>
 * 给定数组arr， arr中的所有值都为正数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，
 * 再给定一个正数aim 代表要找的钱数， 求换钱有多少种方法
 */
public class ExchangeMoneyCount {


    /**
     * 暴力求解
     * 不断递归
     *
     * @param arr
     * @param aim
     * @return
     */
    public int coins1(int[] arr, int aim) {
        if (arr == null || aim < 0) {
            return -1;
        }
        return dfs(arr, 0, aim);
    }

    private int dfs(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += dfs(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    /**
     * 使用缓存技术 ， 把计算过的值全部保存起来。
     * <p>
     * 后续不需要重复计算
     *
     * @param arr
     * @param aim
     * @return
     */
    public int conins2(int[] arr, int aim) {
        int[][] map = new int[arr.length + 1][aim + 1];

        return dfs2(arr, 0, aim, map);
    }

    private int dfs2(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim; i++) {
                mapValue = map[index + 1][aim - arr[index] * i];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += dfs2(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }


    public int coins3(int[] arr, int aim) {
        if (arr == null || aim < 0) {
            return -1;
        }

        /*
         * 动态规划来弄
         *
         * dp[0][..] 标识只能用arr[0]这种面值的货币情况下， 组成钱的方法数
         *
         * 除了第一行第一列的其他位置，  i , j
         *
         * dp[i][j] 表示 组成aim 的方法数
         * 完全不用货币 arr[i] 货币， 直用arr[0..i-1]货币 ， 方法数为 dp[i - 1][j]
         * 用一张arr[i], 剩下的钱用arr[i-1] 则 dp[i-1][j - arr[i]]
         *
         * */

        int m = arr.length;
        int[][] dp = new int[m][aim + 1];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }

        int num = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                num = 0;
                for (int k = 0; j - arr[i] * k > 0; k++) {
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[arr.length - 1][aim];
    }


}
