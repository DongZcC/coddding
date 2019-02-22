package com.dzc.learn.letcode;

import java.util.Arrays;

public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[nums1.length];
        int i = 0;
        int x = 0;
        int y = 0;
        while (i < result.length) {
            if (x < m && y < n) {
                if (nums1[x] <= nums2[y]) {
                    result[i++] = nums1[x++];
                } else {
                    result[i++] = nums2[y++];
                }
            } else if (x < m) {
                result[i++] = nums1[x++];
            } else if (y < n) {
                result[i++] = nums2[y++];
            }
        }
        System.arraycopy(result, 0, nums1, 0, result.length);
    }


    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }


    public static void main(String[] args) {
        MergeSortedArray ms = new MergeSortedArray();
        int[] n1 = new int[]{1, 2, 3, 0, 0, 0};
        ms.merge(n1, 3, new int[]{2, 5, 6}, 3);
        Arrays.stream(n1).forEach(System.out::println);
    }
}
