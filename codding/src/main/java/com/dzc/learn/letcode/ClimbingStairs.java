package com.dzc.learn.letcode;

public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n < 2)
            return 1;
        int[] stairs = new int[n];
        stairs[0] = 1;  // 上第一阶台阶 只有一种情况
        stairs[1] = 2;  // 上第二阶台阶 有两种情况

        // 上第三阶台阶的情况就是有 3种
        // 第四阶台阶的情况有 5 种
        for (int i = 2; i < n; i++) {
            stairs[i] = stairs[i - 1] + stairs[i - 2];
        }
        return stairs[n - 1];
    }

    public static void main(String[] args) {
        ClimbingStairs cs = new ClimbingStairs();
        cs.climbStairs(4);
    }

}
