package com.dzc.learn.letcode;

import java.util.Deque;
import java.util.LinkedList;

public class TrappingRainWater {

    public int trap(int[] height) {
        if (height == null || height.length < 2)
            return 0;
        Deque<Integer> stack = new LinkedList<>();
        int water = 0;
        int current = 0;
        while (current < height.length) {
            if (stack.isEmpty() || height[current] <= height[stack.peek()]) {
                stack.push(current++);
            } else {
                int pre = stack.pop();
                if (!stack.isEmpty()) {
                    // find the smaller height between the two sides
                    int minHeight = Math.min(height[stack.peek()], height[current]);
                    // calculate the area
                    water += (minHeight - height[pre]) * (current - stack.peek() - 1);
                }
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        TrappingRainWater tr = new TrappingRainWater();
        tr.trap(height);
    }
}
