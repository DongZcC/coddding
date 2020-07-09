package com.dzc.interview.sort;

import java.util.*;

/**
 * @author Administrator
 * @date 2020-07-08 15:33
 */
public class QuickSort {

    public void sort(int[] arr) {
        shuffle(arr);

        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int mid = partition(arr, lo, hi);
            quickSort(arr, lo, mid - 1);
            quickSort(arr, mid + 1, hi);
        }
    }

    private int partition(int[] arr, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int pivot = arr[lo];

        while (true) {
            while (arr[++i] < pivot) {
                if (i == hi) {
                    break;
                }
            }

            while (arr[--j] > pivot) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, lo, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void shuffle(int[] arr) {
        int n = arr.length;
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < arr.length; i++) {
            int t = i + r.nextInt(n - i);  // between i and  n - 1
            int tmp = arr[t];
            arr[t] = arr[i];
            arr[i] = tmp;
        }
    }

    public static void main(String[] args) {
        QuickSort s = new QuickSort();
        int[] array = new int[]{5, 6, 1, 2, 3, 4, 7};
        s.sort(array);
        Arrays.stream(array).forEach(i -> System.out.println(i));
    }
}
