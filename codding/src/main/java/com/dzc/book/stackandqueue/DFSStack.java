package com.dzc.book.stackandqueue;

import java.util.LinkedList;

/**
 * @author Administrator
 * @date 2020-05-13 17:01
 * <p>
 * 递归函数来逆序一个栈.
 */
public class DFSStack {

    private LinkedList<Integer> stack;

    public DFSStack() {
        stack = new LinkedList<>();
    }

    public void push(Integer i) {
        stack.push(i);
    }

    public Integer pop() {
        return null;
    }

    public void reverse() {
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse();
        stack.push(i);
    }

    /**
     *  3
     *  2
     *  1
     *
     *  获取到 1
     * @param stack
     * @return
     */
    private int getAndRemoveLastElement(LinkedList<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        DFSStack stack = new DFSStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);


        stack.reverse();
        System.out.println(stack);
    }
}
