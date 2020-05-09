package com.dzc.learn.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {


    public static void main(String[] args) {
        int[] array = new int[]{5, 3, 6, 1, 0, 8};
        sort(array);
        Arrays.stream(array).forEach(System.out::println);
    }

    private static void sort(int[] array) {
        int[] tmp = new int[array.length];
        mergeSort(array, tmp, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int[] tmp, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, tmp, start, mid);
            mergeSort(array, tmp, mid + 1, end);
            merge(array, tmp, start, mid + 1, end);
        }
    }

    private static void merge(int[] array, int[] tmp, int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while (left <= leftEnd && right <= rightEnd) {
            if (array[left] <= array[right]) {
                tmp[k++] = array[left++];
            } else {
                tmp[k++] = array[right++];
            }
        }

        while (left <= leftEnd) {
            tmp[k++] = array[left++];
        }

        while (right <= rightEnd) {
            tmp[k++] = array[right++];
        }

        for (int i = 0; i < num; i++, rightEnd--) {
            array[rightEnd] = tmp[rightEnd];
        }
    }
}
