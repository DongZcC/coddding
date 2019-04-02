package com.dzc.learn.concurrency;

import javax.transaction.NotSupportedException;

/**
 * N个线程，循环打印出0至100
 * <p>
 * e.g
 * <p>
 * 通过N个线程顺序循环打印从0至100，如给定N=3则输出:
 * thread0: 0
 * thread1: 1
 * thread2: 2
 * thread0: 3
 * thread1: 4
 */
public class NThread {


    private static final Object lock = new Object();

    private static int count;

    private static int threadCount = 3;

    static class SimpleTask implements Runnable {

        private int threadNo;

        public SimpleTask(int threadNo) {
            this.threadNo = threadNo;
        }


        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (count % threadCount != threadNo) {

                        if (count > 100) {
                            break;
                        }

                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (count > 100) {
                        break;
                    }

                    System.out.println("Thread" + threadNo + " :" + count++);
                    lock.notifyAll();
                }
            }
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new SimpleTask(i)).start();
        }
    }
}
