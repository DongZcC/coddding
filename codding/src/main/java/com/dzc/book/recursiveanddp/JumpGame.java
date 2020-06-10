package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-10 16:49
 * <p>
 * 跳跃游戏
 * <p>
 * 给定数组arr  arr[i] == k 代表可以从i向右条约  1-k 个距离.
 * <p>
 * 如果从位置 0 出发， 返回最少跳几次能跳到arr最后的位置上
 */
public class JumpGame {

    public int num(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }


        int jump = 0;
        int cur = 0;
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                jump++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return jump;
    }
}
