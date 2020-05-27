package com.dzc.book.array;

import java.util.HashMap;

/**
 * @author Administrator
 * @date 2020-05-27 15:09
 * 在未排序数组中累加和为给定值的最长子数组系列问题
 * <p>
 * 给定一个无序数组 arr， 其中元素可正、 可负、 可0 给定一个整数k 求arr所有的子数组中累加和为k的最长子数组长度
 * s(i) = arr[0..i] 的累加和
 * arr[0..j - 1] 的累加和为 s(j-1)
 * arr[j..i] 的累加和为 s(i) - s(j - 1)
 */
public class SearchSumInUnSortedArray {

    public int searchSum(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return 0;
        }
        int sum = 0;
        int len = 0;
        // key 从arr最左边开始累加过程中出现过的sum值 ， value值标识sum值出现最早的位置
        HashMap<Integer, Integer> map = new HashMap<>();
        // 数组从 a[j+1, i] 位置的累加和可以计算数组的第一个位置 0
        map.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(len, i - map.get(sum - k));
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return len;
    }

    // 补充题目， 求arr所有的子数组中， 正数 与 负数 个数相等的最长子数组长度
    public int searchSum2(int[] arr, int k) {
        // 先把数组中的所有正数 变成 1 ， 负数变成 -1 求和为0 的最长子数组长度
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                arr[i] = 1;
            } else if (arr[i] < 0) {
                arr[i] = -1;
            }
        }

        return searchSum(arr, 0);
    }


    // 补充题目 arr的所有子数组中， 只存在 1 0  求所有子数组中 0 1 个数相同的最长数组长度
    public int searchSum3(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }
        return searchSum(arr, 0);
    }


    public static void main(String[] args) {
        SearchSumInUnSortedArray search = new SearchSumInUnSortedArray();
        int i = search.searchSum(new int[]{1, 2, -3, 3, 3}, 6);
        System.out.println(i);
    }
}
