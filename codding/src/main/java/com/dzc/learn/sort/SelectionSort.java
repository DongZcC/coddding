package com.dzc.learn.sort;

/**
 * @author Administrator
 * @date 2020-05-23 14:56
 */
public class SelectionSort {

    public static void selectionSort(int[] arr) {
        // 3 2 1 5 6 8
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            // 如果最小值不是当前值，就
            if (min != i) {
                int tmp = arr[min];
                arr[min] = arr[i];
                arr[i] = tmp;
            }
        }

    }
}
