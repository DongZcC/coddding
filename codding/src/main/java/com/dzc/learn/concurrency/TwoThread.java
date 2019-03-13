package com.dzc.learn.concurrency;

public class TwoThread {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        TwoThread tw = new TwoThread();
        tw.turning();
    }


    private volatile int count = 0;

    public void turning() throws InterruptedException {
        Thread even = new Thread(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println("偶数：" + count++);
                    lock.notifyAll();

                    try {
                        if (count <= 100) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        Thread odd = new Thread(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println("奇数：" + count++);
                    lock.notifyAll();

                    try {
                        if (count <= 100) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        even.start();
        Thread.sleep(1);
        odd.start();
    }
}
