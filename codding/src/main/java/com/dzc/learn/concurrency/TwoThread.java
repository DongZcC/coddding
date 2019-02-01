package com.dzc.learn.concurrency;

public class TwoThread {

    private static volatile boolean flag = false;

    private static int num1 = 1;

    private static int num2 = 2;

    private static int end = 10;

    private static final Object lock = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread("T1") {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        if (flag) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        flag = true;
                        if (num1 > end)
                            break;
                        System.out.println(num1);
                        num1 += 2;
                        lock.notifyAll();
                    }
                }
            }
        };

        Thread t2 = new Thread("T2") {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        if (!flag) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        flag = false;
                        if (num2 > end)
                            break;
                        System.out.println(num2);
                        num2 += 2;
                        lock.notifyAll();
                    }
                }
            }
        };

        t2.start();
        t1.start();


        // 最终总会有一个线程在等待 ， 不会退出
//        synchronized (lock) {
//            lock.notifyAll();
//        }
    }
}
