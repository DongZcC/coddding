package com.dzc.book.stackandqueue;

import java.util.LinkedList;

/**
 * @author Administrator
 * @date 2020-05-15 17:07
 * <p>
 * 用一个栈实现另一个栈的排序
 */
public class UseSingleStackSort {

    public static void main(String[] args) {

        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(5);
        stack.push(3);
        stack.push(4);
        stack.push(2);


        /*
         * 2
         * 4
         * 3
         * 5
         * 1
         * */

        // 现在想实现栈从顶到底 按照从大到小的顺序排序.

        sort(stack);

    }

    private static void sort(LinkedList<Integer> stack) {
        LinkedList<Integer> tmp = new LinkedList<>();

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!tmp.isEmpty() && cur > tmp.peek()) {
                stack.push(tmp.pop());
            }
            tmp.push(cur);
        }

        while (!tmp.isEmpty()) {
            stack.push(tmp.pop());
        }

    }
}
