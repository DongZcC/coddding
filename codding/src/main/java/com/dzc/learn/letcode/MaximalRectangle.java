package com.dzc.learn.letcode;


import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] heigths = getHistogram(matrix, i);
            int area = largestRectangleArea(heigths);
            max = Math.max(max, area);
        }
        return max;
    }

    private int largestRectangleArea(int[] heigths) {
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        int maxArea = 0;
        int[] h = new int[heigths.length + 1];
        h = Arrays.copyOf(heigths, heigths.length + 1);
        while (i < h.length) {
            // stack中只存放单调递增的索引
            if (stack.isEmpty() || h[i] >= h[stack.peek()]) {
                stack.push(i++);
            } else {
                int t = stack.pop();
                maxArea = Math.max(maxArea, h[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return maxArea;
    }

    private int[] getHistogram(char[][] matrix, int row) {
        int[] heights = new int[matrix[0].length];
        boolean[] hasEnd = new boolean[matrix[0].length];
        // 由于这样是从后往前的; 因此只要这列是 0 , 那么以后的就全部是 0 了
        for (int i = row; i >= 0; i--) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1' && !hasEnd[j]) {
                    heights[j]++;
                } else {
                    hasEnd[j] = true;
                }
            }
        }
        return heights;
    }
}
