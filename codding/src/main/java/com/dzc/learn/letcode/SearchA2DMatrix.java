package com.dzc.learn.letcode;


public class SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        // 这个 矩阵整体是有序的啊
        int row = matrix.length;
        int col = matrix[0].length;
        int begin = 0, end = row * col - 1;
        while (begin <= end) {
            int mid = (begin + end) >>> 1;
            int midValue = matrix[mid / col][mid % col];
            if (midValue == target)
                return true;
            else if (midValue < target) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int i = -2;
        int j = 2;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(j));
        System.out.println(Integer.toBinaryString(i >> 1));

    }

}
