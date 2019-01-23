package com.dzc.learn.letcode;

import java.util.ArrayList;
import java.util.List;

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        List<Integer> cols = new ArrayList<>();
        List<Integer> rows = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (Integer col : cols) {
                matrix[i][col] = 0;
            }
        }

        for (Integer row : rows) {
            matrix[row] = new int[matrix[0].length];
        }
    }
}
