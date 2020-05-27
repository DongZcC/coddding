package com.dzc.book.array;

/**
 * @author Administrator
 * @date 2020-05-27 10:16
 * <p>
 * 未排序正数数组中累加和给定值的最长子数组长度
 * <p>
 * 给定一个数组arr 数组无序， 给定一个正数k 。
 * 例如 arr = [1,2,1,1,1] , k = 3
 * 累加和为3的最长数组为[1,1,1] 所以返回结果3
 */
public class SearchSumInArray {

    public int searchSumArrLen(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == k) {
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            } else if (sum < k) {
                sum += arr[right++];
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        SearchSumInArray search = new SearchSumInArray();

        int len = search.searchSumArrLen(new int[]{1, 2, 1, 1, 1, 1}, 3);

        System.out.println(len);

    }
}
