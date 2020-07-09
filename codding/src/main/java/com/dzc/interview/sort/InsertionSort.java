package com.dzc.interview.sort;

import java.util.Arrays;

/**
 * @author Administrator
 * @date 2020-07-08 14:47
 * <p>
 * 插入排序
 */
public class InsertionSort {

    public void sort(int[] arr) {
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = i + 1; j > 0; j--) {
//                if (arr[j] < arr[j - 1]) {
//                    int tmp = arr[j];
//                    arr[j] = arr[j - 1];
//                    arr[j - 1] = tmp;
//                }
//            }
//        }
        //
        for (int i = 1; i < arr.length; i++) {
            int preIndex = i - 1;
            int cur = arr[i];
            while (preIndex >= 0 && arr[preIndex] > cur) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = cur;
        }
    }

    public static void main(String[] args) {
        InsertionSort s = new InsertionSort();
        int[] array = new int[]{5, 6, 1, 2, 3, 4, 7};
        s.sort(array);
        Arrays.stream(array).forEach(i -> System.out.println(i));
    }
}
