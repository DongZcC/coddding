package com.dzc.book.stackandqueue;

import java.util.LinkedList;

/**
 * @author Administrator
 * @date 2020-05-13 15:21
 * <p>
 * 实现一个特殊的栈， 在实现栈的基本功能基础上， 在实现返回栈中最小元素的操作.
 * <p>
 * <p>
 * 入栈： 3 2 4 5 1 10
 * <p>
 * stackData -> 3 2 4 5 1 10
 * stackMin -> 3 2 1
 */
public class GetMinStack<T extends Comparable<T>> {

    private LinkedList<T> stackData;

    private LinkedList<T> stackMin;


    public GetMinStack() {
        stackData = new LinkedList<>();
        stackMin = new LinkedList<>();
    }

    public void push(T t) {
        stackData.push(t);

        if (stackMin.isEmpty()) {
            stackMin.push(t);
        } else {
            T min = stackMin.peek();
            if (t.compareTo(min) <= 0) {
                stackMin.push(t);
            }
        }
    }

    public T pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }

        T res = stackData.pop();
        if (res == stackMin.peek()) {
            stackMin.pop();
        }
        return res;
    }


    public T getMin() {
        return stackMin.peek();
    }

}
