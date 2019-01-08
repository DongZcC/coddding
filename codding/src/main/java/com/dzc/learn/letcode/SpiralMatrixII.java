package com.dzc.learn.letcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 */
public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int left = 0, right = n - 1;
        int top = 0, bottom = n - 1;

        int index = 1;

        while (index <= n * n) {
            for (int i = left; i <= right; i++) {
                result[top][i] = index++;
            }

            for (int i = top + 1; i <= bottom; i++) {
                result[i][right] = index++;
            }

            for (int i = right - 1; i >= left; i--) {
                result[bottom][i] = index++;
            }

            for (int i = bottom - 1; i > top; i--) {
                result[i][left] = index++;
            }

            top++;
            left++;
            right--;
            bottom--;
        }

        return result;
    }

    public static void main(String[] args) {
        SpiralMatrixII s = new SpiralMatrixII();
        s.generateMatrix(4);
    }
}
