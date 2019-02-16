package com.dzc.learn.letcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class LargestRectangleinHistogram {

    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                if (heights[j] < minHeight) {
                    minHeight = heights[j];
                }
                ans = Math.max(ans, minHeight * (j - i + 1));
            }
        }
        return ans;
    }


    public int largestRectangleAreaStack(int[] height) {
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        int maxArea = 0;
        int[] h = new int[height.length + 1];
        h = Arrays.copyOf(height, height.length + 1);
        while (i < h.length) {
            if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
                stack.push(i++);
            } else {
                int t = stack.pop();
                maxArea = Math.max(maxArea, h[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return maxArea;
    }


    public int largestRectangleAreaDP(int[] heights) {
        int len = heights.length;

        int[] left = new int[heights.length];
        int[] right = new int[heights.length];

        // 循环找到当前位置左侧可以的宽度
        for (int i = 0; i < heights.length; i++) {
            int k = i;
            while (k > 0 && heights[i] <= heights[k - 1]) {
                k = left[k - 1];
            }
            left[i] = k;
        }

        // 循环找到右侧位置可以到的宽度
        for (int i = heights.length - 1; i >= 0; i--) {
            int k = i;
            while (k < heights.length - 1 && heights[i] <= heights[k + 1]) {
                k = right[k + 1];
            }
            right[i] = k;
        }

        int max = 0;

        for (int i = 0; i < len; i++) {
            max = Math.max(max, (right[i] - left[i] + 1) * heights[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        LargestRectangleinHistogram lr = new LargestRectangleinHistogram();
        lr.largestRectangleAreaDP(new int[]{2, 1, 5, 6, 2, 3});
    }
}
