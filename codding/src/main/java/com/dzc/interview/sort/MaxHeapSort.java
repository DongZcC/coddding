package com.dzc.interview.sort;

import java.util.Arrays;

/**
 * @author Administrator
 * @date 2020-07-08 19:17
 */
public class MaxHeapSort {


    public void sort(int[] array) {
        buildMaxHeap(array);

        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);

            maxHeapify(array, 0, i);
        }
    }

    private void buildMaxHeap(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            maxHeapify(array, i, array.length);
        }
    }

    private void maxHeapify(int[] arr, int parentIndex, int heapSize) {
        int leftChild = 2 * parentIndex + 1;
        int rightChild = 2 * parentIndex + 2;

        int largest = parentIndex;
        if (leftChild < heapSize && arr[leftChild] > arr[parentIndex]) {
            largest = leftChild;
        }

        if (rightChild < heapSize && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }
        if (largest != parentIndex) {
            // 交换 .
            swap(arr, largest, parentIndex);

            // 重新构建最大堆
            maxHeapify(arr, largest, heapSize);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        MaxHeapSort s = new MaxHeapSort();
        int[] array = new int[]{5, 6, 1, 2, 3, 4, 7};
        s.sort(array);
        Arrays.stream(array).forEach(i -> System.out.println(i));
    }
}
