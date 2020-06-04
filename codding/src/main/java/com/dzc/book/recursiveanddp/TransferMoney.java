package com.dzc.book.recursiveanddp;

import java.util.*;

/**
 * @author Administrator
 * @date 2020-06-04 13:26
 * <p>
 * 换钱的最少货币数
 * <p>
 * 给定数组arr ，arr中所有的值都是整数且不重复。 每个值代表一种面值的货币， 每种面值的货币可以使用任议长。
 * <p>
 * 给定一个整数 aim 代表要找的钱数， 求组成aim的最少货币数
 * <p>
 * arr[5,2,3] aim = 20
 */
public class TransferMoney {

    List<List<Integer>> results = new ArrayList<>();

    /**
     * 递归 暴力穷举所有可能性
     *
     * @param arr
     * @param aim
     * @return
     */
    public int transfer1(int[] arr, int aim) {
        if (aim == 0 || arr == null) {
            return 0;
        }
        // 直接用递归. dfs进行计算.
        dfs(arr, 0, 0, new ArrayList<Integer>(), aim);

        OptionalInt min1 = results.stream().mapToInt(l -> {
            Set<Integer> set = new HashSet<>(l);
            return set.size();
        }).min();

        // 补充题目
        OptionalInt min = results.stream().mapToInt(List::size).min();
        if (min.isPresent()) {
            return min.getAsInt();
        } else {
            return -1;
        }
    }

    private void dfs(int[] arr, int index, int sum, ArrayList<Integer> tmp, int aim) {
        if (sum > aim) {
            return;
        }
        if (sum == aim) {
            results.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            tmp.add(arr[i]);
            sum += arr[i];
            dfs(arr, i, sum, tmp, aim);
            tmp.remove((Integer) arr[i]);
            sum -= arr[i];
        }
    }


    /**
     * dp[arr.length][aim+1]
     * <p>
     * dp[i][j] = 可以任意使用arr[0..i]货币的情况下， 组成j 所需要的最小张树
     * <p>
     * dp[0..N-1][0] 代表钱数为 0 时候需要要的最小张数 。  0
     * dp[0][0...aim] 标识只能用arr[0]的情况下 找某个签署的最小张数
     *
     * @param arr
     * @param aim
     * @return
     */
    public int transfer(int[] arr, int aim) {
        if (arr == null || aim < 0) {
            return -1;
        }
        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[][] dp = new int[n][aim + 1];

        for (int i = 1; i <= aim; i++) {
            dp[0][i] = max;
            if (i - arr[i] >= 0 && dp[0][i - arr[i]] != max) {
                dp[0][i] = dp[0][i - arr[i]] + 1;
            }
        }

        int left = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                left = max;
                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != max) {
                    left = dp[i][j - arr[i]] + 1;
                }

                dp[i][j] = Math.min(left, dp[i - 1][j]);
            }
        }
        return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
    }

    public static void main(String[] args) {
        TransferMoney trans = new TransferMoney();
        int i = trans.transfer1(new int[]{5, 2, 3}, 20);
        System.out.println(i);
    }
}
