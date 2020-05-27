package com.dzc.book.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2020-05-27 16:10
 * 未排序数组中累加和小于或等于给定值的最长子数组长度
 * <p>
 * 给定数组arr， k 求arr所有子数组中累加和小于或者等于k的最长子数组长度
 */
public class SearchInUnSortedArrayLEK {

    List<List<Integer>> results = new ArrayList<>();

    // arr[3,-2,-4,0,6] , k = -2 相加和小于等于-2 的最长子数组为 {3,-2,-4,0}
    public int searchInArray(int[] arr, int k) {
        // dfs暴力穷举， 穷举所有子数组
        dfs(arr, 0, new ArrayList<Integer>());

        int len = 0;
        for (List<Integer> result : results) {

            Integer sum = result.stream().reduce(0, Integer::sum);
            if (sum <= k) {
                len = Math.max(len, result.size());
            }
        }
        return len;
    }

    public int searchInArray2(int[] arr, int k) {
        int[] sumArray = new int[arr.length + 1];
        sumArray[0] = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            sumArray[i + 1] = Math.max(sum, sumArray[i]);
        }

        sum = 0;
        int len = 0;
        int pre = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(sumArray, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    private int getLessIndex(int[] arr, int i) {
        int lo = 0;
        int hi = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if (arr[mid] >= i) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }

    private void dfs(int[] arr, int index, ArrayList<Integer> tmpList) {
        results.add(new ArrayList<>(tmpList));
        if (index == arr.length) {
            return;
        }

        for (int i = index; i < arr.length; i++) {
            tmpList.add(arr[i]);
            dfs(arr, i + 1, tmpList);
            tmpList.remove((Integer) arr[i]);
        }
    }

    public static void main(String[] args) {
        SearchInUnSortedArrayLEK s = new SearchInUnSortedArrayLEK();
        int len = s.searchInArray2(new int[]{3, -2, -4, 0, 6}, -2);
        System.out.println(len);
    }
}
