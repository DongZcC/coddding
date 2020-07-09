package com.dzc.interview.sort;

import java.util.Arrays;

/**
 * @author Administrator
 * @date 2020-07-08 14:41
 * <p>
 * 选择排序
 */
public class SelectionSort {

    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (i != minIndex) {
                int tmp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        SelectionSort s = new SelectionSort();
        int[] array = new int[]{5, 6, 1, 2, 3, 4, 7};
        s.sort(array);


        Arrays.stream(array).forEach(i -> System.out.println(i));
    }
}
