package com.dzc.interview.sort;

import java.util.Arrays;

/**
 * @author Administrator
 * @date 2020-07-08 14:28
 * <p>
 * 选择排序.
 */
public class BubbleSort {


    public void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }


    public static void main(String[] args) {
        BubbleSort s = new BubbleSort();
        int[] array = new int[]{5, 6, 1, 2, 3, 4, 7};
        s.sort(array);


        Arrays.stream(array).forEach(i -> System.out.println(i));
    }
}
