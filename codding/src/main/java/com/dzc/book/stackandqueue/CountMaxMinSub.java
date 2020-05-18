package com.dzc.book.stackandqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 * @date 2020-05-18 15:28
 * <p>
 * 最大值减去最小值 小于 或等于 num 的 子数组 数量
 */
public class CountMaxMinSub {

    List<List<Integer>> sub = new ArrayList<>();

    public int getDiffCount(int[] arr, int num) {

        // 首先找到数组的所有子数组
        // 求子集，每一位都只有两种状态，在子集中或不在子集中。那把每种情况都输出就可以了
        // dfs 获取数组的所有子集
        int res = 0;
        dfs(arr, 0, new ArrayList<Integer>());
        for (List<Integer> integers : sub) {
            Integer max = Collections.max(integers);
            Integer min = Collections.min(integers);
            if (max - min <= num) {
                res++;
            }
        }
        return res;
    }

    private void dfs(int[] arr, int startIndex, ArrayList<Integer> tmpList) {
        if (!tmpList.isEmpty()) {
            sub.add(new ArrayList<>(tmpList));
        }

        if (startIndex >= arr.length) {
            return;
        }


        for (int i = startIndex; i < arr.length; i++) {
            tmpList.add(arr[i]);
            dfs(arr, i + 1, tmpList);
            tmpList.remove((Integer) arr[i]);
        }
    }



    public int getDiffCount2(int[] arr, int num) {
        int res = 0;
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int i = 0;
        int j = 0;

        while (i < arr.length) {
            while (j < arr.length) {
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
                    qmin.pollLast();
                }

                qmin.addLast(j);

                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
                    qmax.pollLast();
                }
                qmax.addLast(j);

                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                j++;
            }

            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == i) {
                qmax.pollFirst();
            }

            res += j - i;
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        CountMaxMinSub count = new CountMaxMinSub();
        count.getDiffCount2(new int[]{1, 2, 3}, 0);
    }
}
