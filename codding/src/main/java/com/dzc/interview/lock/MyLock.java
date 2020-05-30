package com.dzc.interview.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author Administrator
 * @date 2020-05-28 20:25
 * 自己动手写一个锁
 */
public class MyLock {

    private static Unsafe unsafe;

    private static long stateOffset;

    private static long tailOffset;


    static final Node EMPTY = new Node();

    private volatile Node head;

    private volatile Node tail;

    private volatile int state;


    static {

        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            stateOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("state"));
            tailOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("tail"));
        } catch (Exception e) {
        }
    }

    private static class Node {

        Thread thread;

        Node prev;

        Node next;

        public Node() {
        }


        public Node(Thread thread, Node prev) {
            this.thread = thread;
            this.prev = prev;
        }
    }


    public MyLock() {
        head = tail = EMPTY;
    }


    private boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    private boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }


    public void lock() {
        if (compareAndSetState(0, 1)) {
            return;
        }

        Node node = enqueue();
        Node prev = node.prev;

        while (node.prev != head || !compareAndSetState(0, 1)) {
            // 没有获取到锁 阻塞住
            unsafe.park(false, 0L);
        }

        // 下面就是获取到锁的操作了
        head = node;

        // 第一个节点的线程都是空的 ?
        node.thread = null;
        node.prev = null;
        prev.next = null;
    }

    public void unlock() {
        state = 0;
        Node next = head.next;
        if (next != null) {
            unsafe.unpark(next.thread);
        }
    }


    private Node enqueue() {
        while (true) {
            Node t = tail;
            Node node = new Node(Thread.currentThread(), t);
            if (compareAndSetTail(t, node)) {
                t.next = node;
                return node;
            }
        }
    }


    private static int  count = 0;


    public static void main(String[] args) throws InterruptedException {
        MyLock lock = new MyLock();
        CountDownLatch latch = new CountDownLatch(1000);
        IntStream.range(0, 1000).forEach(i -> new Thread(() -> {
            lock.lock();
            try {
                IntStream.range(0, 10000).forEach(j -> {
                    count++;
                });
            } finally {
                lock.unlock();
            }
            latch.countDown();
        }).start());

        latch.await();
        System.out.println(count);
    }
}
