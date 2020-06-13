package com.dzc.book.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020-06-13 16:39
 * <p>
 * <p>
 * 判断字符数组中所有字符都只出现过一次
 */
public class UniqueString {

    public boolean isUnique1(char[] chas) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chas.length; i++) {
            Integer k = map.get(chas[i]);
            if (k == null) {
                map.put(chas[i], 1);
            } else {
                return false;
            }
        }
        return true;
    }

    // 要求 空间复杂度 O(1)
    // 基于堆排序 进行排序， 然后遍历一下 看下当前字符是否和前一个相同
    public boolean isUnique2(char[] chas) {
        if (chas == null || chas.length == 0) {
            return false;
        }
        heapSort(chas);

        return false;
    }

    private void heapSort(char[] chas) {
        for (int i = 0; i < chas.length; i++) {
            // 生成了一个最大堆 .
            heapInsert(chas, i);
        }
        for (int i = chas.length - 1; i > 0; i--) {
            swap(chas, 0, i);
            heapify(chas, 0, i);
        }

    }

    private void heapInsert(char[] chas, int i) {
        int parent = 0;
        while (i != 0) {
            parent = (i - 1) / 2;
            if (chas[parent] < chas[i]) {
                swap(chas, parent, i);
                i = parent;
            } else {
                break;
            }
        }
    }

    private void heapify(char[] chas, int i, int size) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;

        while (left < size) {
            if (chas[left] > chas[i]) {
                largest = left;
            }
            if (right < size && chas[right] > chas[largest]) {
                largest = right;
            }
            if (largest != i) {
                swap(chas, largest, i);
            } else {
                break;
            }
            i = largest;
            left = i * 2 + 1;
            right = i * 2 + 2;
        }
    }

    private void swap(char[] chas, int i, int j) {
        char tmp = chas[i];
        chas[i] = chas[j];
        chas[j] = tmp;
    }


}
