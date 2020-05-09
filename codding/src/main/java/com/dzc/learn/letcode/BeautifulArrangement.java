package com.dzc.learn.letcode;

import java.util.Arrays;

public class BeautifulArrangement {

    int count = 0;

    public void find(int[] arr, int element) {
        if (element == arr.length) {
            Arrays.stream(arr).forEach(System.out::print);
            System.out.println();
            count++;
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == -1) {
                if (i % element == 0 || element % i == 0) {
                    arr[i] = element;
                    find(arr, element + 1);
                    arr[i] = -1;
                }
            }
        }
    }

    public int countArrangement(int N) {
        int[] arr = new int[N + 1];
        Arrays.fill(arr, -1);
        find(arr, 1);
        return count;
    }


    public static void main(String[] args) {
        BeautifulArrangement b = new BeautifulArrangement();
        b.countArrangement(4);
    }
}
