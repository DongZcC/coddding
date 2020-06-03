package com.dzc.book.recursive;

/**
 * @author Administrator
 * @date 2020-06-03 15:08
 * <p>
 * <p>
 * 斐波那契系列问题的递归和动态规划
 */
public class Fib {

    /**
     * 给定一个整数 N， 代表台阶数， 一次可以跨2个或者一个台阶 返回有多少种走法
     * <p>
     * 复杂度 O(2^n)
     *
     * @param N
     * @return
     */
    public int fib(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        return fib(N - 1) + fib(N - 2);
    }

    /**
     * 复杂读 O(N)
     *
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] num = new int[n + 1];
        num[0] = 1;
        num[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            num[i] = num[i - 1] + num[i - 2];
        }
        return num[n];
    }

}
