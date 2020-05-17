package com.dzc.book.stackandqueue;

import java.util.LinkedList;

/**
 * Created on 2020/5/17.
 * <p>
 * <p>
 * 生成窗口最大值数组
 *
 * @author _Shrimp
 */
public class MaxWindowArray {

    public static void main(String[] args) {
        MaxWindowArray m = new MaxWindowArray();
        m.MaxWindowArray(new int[]{4, 3, 5, 4, 3, 3, 6, 7}, 3);
    }

    /**
     * @param arr 数组array
     * @param w   窗口大小为 w
     * @return 长度为 n-w + 1 的数组 res . res[i]表示每一种窗口状态下的最大值.
     */
    public int[] MaxWindowArray(int[] arr, int w) {

        // [4,3,5,4,3,3,6,7]
        // 窗口大小为 3
        // [5, 5, 5, 4, 6, 7]

        int n = arr.length;
        int[] res = new int[n - w + 1];


        /*

        第一种解法： 复杂度是 O (m * n)
        for (int i = 0; i < arr.length - w + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + w; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            res[i] = max;
        }
         */


        // 使用双端队列来实现窗口最大值的更新。
        // 生成双端队列 qmax， qmax中存放数组arr的下标
        // 假设遍历到arr[i], qmax中的放入规则为:
        // 1. qmax为空, 直接把下标i 放入qmax中
        // 2. qmax不为空, 取出当前qmax队尾存放的下表, 假设为j
        // 3. 如果arr[j] > arr[i] 直接把下表i放到qmax的对尾
        // 4. 如果arr[j] <= arr[i] 把j从qmax中弹出，继续qmax的放入规则


        int index = 0;
        LinkedList<Integer> qmax = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {

            while (!qmax.isEmpty() && arr[qmax.peekLast()] < arr[i]) {
                qmax.pollLast();
            }

            // qmax中对头存的是一个区间窗口w. 一个最大值的下标
            qmax.add(i);

            // 这个下标要有一个过期的时间.
            // 当当前的窗口w不包含 下标的时候就要弹出了
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }

            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }

        }

        return res;
    }

}
