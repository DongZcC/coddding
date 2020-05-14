package com.dzc.book.stackandqueue;

import java.util.LinkedList;

/**
 * @author Administrator
 * @date 2020-05-13 15:39
 * <p>
 * 由两个栈组成的队列
 * <p>
 * 入队： 3 2 4 5 1 10
 * <p>
 * stack1
 * <p>
 * stack2
 */
public class TowStackQueue {


    private LinkedList<Integer> stackPush;

    private LinkedList<Integer> stackPop;

    public TowStackQueue() {
        stackPush = new LinkedList<>();
        stackPop = new LinkedList<>();
    }


    public void add(Integer i) {
        stackPush.push(i);
    }

    public Integer pool() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty())
                stackPop.push(stackPush.pop());
        }
        return stackPop.pop();
    }

    public Integer peek() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty())
                stackPop.push(stackPush.pop());
        }
        return stackPop.peek();
    }

    public static void main(String[] args) {
        TowStackQueue queue = new TowStackQueue();
        queue.add(3);
        queue.add(2);
        queue.add(4);
        queue.add(5);
        queue.add(1);
        queue.add(10);


        System.out.println(queue.pool());
        System.out.println(queue.pool());
        System.out.println(queue.pool());
        System.out.println(queue.peek());
        System.out.println(queue.pool());
        System.out.println(queue.pool());
        System.out.println(queue.pool());
    }
}
