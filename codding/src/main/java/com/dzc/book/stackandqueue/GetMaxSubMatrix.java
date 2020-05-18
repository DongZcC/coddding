package com.dzc.book.stackandqueue;

import java.util.Stack;

/**
 * @author Administrator
 * @date 2020-05-18 13:47
 * <p>
 * <p>
 * 获取最大子矩阵面积
 *
 * 这个问题， 转换成一个 直方图求面积的问题
 *
 * 这个直方图 求面积， 就是考虑当前位置栈 ； 到左边 到右边 到底能有多长  最后 长 * 宽 得到一个面积
 *
 * 获取一个最大面积
 *
 */
public class GetMaxSubMatrix {

    public int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] heights = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                heights[j] = map[i][j] == 0 ? 0 : heights[j] + 1;
            }
            maxArea = Math.max(maxArea, maxRecFromBottom(heights));
        }
        return maxArea;
    }

    private int maxRecFromBottom(int[] height) {

        if (height == null || height.length == 0) {
            return 0;
        }

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }


        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        GetMaxSubMatrix max = new GetMaxSubMatrix();
        max.maxRecFromBottom(new int[]{});
    }
}
