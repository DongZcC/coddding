package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-10 19:09
 * <p>
 * N皇后问题
 */
public class NQueen {


    public int nQueen(int n) {
        if (n < 0) {
            return 0;
        }
        int[] record = new int[n];
        return process(0, record, n);
    }

    private int process(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }

        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process(i + 1, record, n);
            }
        }
        return res;
    }

    private boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }
}
