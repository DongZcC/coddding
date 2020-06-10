package com.dzc.book.recursiveanddp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020-06-10 17:01
 * <p>
 * 数组中的最长连续序列
 * <p>
 * 给定无序数组 arr, 返回其中最长的连续序列的长度
 */
public class LongestSeqInArray {


    public int longestConsecutive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int max = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                continue;
            }
            map.put(arr[i], 1);
            if (map.containsKey(arr[i] - 1)) {
                max = Math.max(max, merge(map, arr[i] - 1, arr[i]));
            }

            if (map.containsKey(arr[i] + 1)) {
                max = Math.max(max, merge(map, arr[i], arr[i] + 1));

            }
        }
        return max;
    }

    private int merge(Map<Integer, Integer> map, int less, int more) {
        int left = less - map.get(less) + 1;
        int right = more + map.get(more) - 1;
        int len = right - left + 1;
        map.put(left, len);
        map.put(right, len);
        return len;
    }
}
