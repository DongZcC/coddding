package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-08 15:47
 * <p>
 * 排成一条线的纸牌博弈问题
 * <p>
 * 给定一个整型数组arr ， 代表数值不同的纸牌 排成一条线
 * <p>
 * 玩家A和玩家B依次拿走每张纸牌，规定A先拿 B后拿 每次只能拿走最左或者最右
 */
public class PlayingCard {

    /**
     * 暴力递归求解
     *
     * @param arr
     * @return
     */
    public int score(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private int process(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        int res = 0;
        res = Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
        return res;
    }

    private int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        return Math.min(process(arr, i + 1, j), process(arr, i, j - 1));
    }
}
