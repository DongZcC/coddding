package com.dzc.interview.sort;

import java.util.Arrays;

/**
 * @author Administrator
 * @date 2020-07-08 15:07
 */
public class MergeSort {

    public void sort(int[] arr) {
        int[] tmp = new int[arr.length];
        mergeSort(arr, tmp, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int[] tmp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, tmp, left, mid);
            mergeSort(arr, tmp, mid + 1, right);
            merge(arr, tmp, left, mid + 1, right);
        }
    }

    private void merge(int[] arr, int[] tmp, int leftStart, int rightStart, int rightEnd) {
        int leftEnd = rightStart - 1;
        int k = leftStart;
        int num = rightEnd - leftStart + 1;


        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (arr[leftStart] < arr[rightStart]) {
                tmp[k++] = arr[leftStart++];
            } else {
                tmp[k++] = arr[rightStart++];
            }
        }

        while (leftStart <= leftEnd) {
            tmp[k++] = arr[leftStart++];
        }

        while (rightStart <= rightEnd) {
            tmp[k++] = arr[rightStart++];
        }

        for (int i = 0; i < num; i++, rightEnd--) {
            arr[rightEnd] = tmp[rightEnd];
        }
    }


    public static void main(String[] args) {
        MergeSort s = new MergeSort();
        int[] array = new int[]{5, 6, 1, 2, 3, 4, 7};
        s.sort(array);
        Arrays.stream(array).forEach(i -> System.out.println(i));
    }
}
