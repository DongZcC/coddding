package com.dzc.learn.concurrency;

import java.util.concurrent.Semaphore;


/**
 * 通过用Semaphore 来控制线程
 * 很吊
 * 很经典
 * 学到了
 */
public class NThread2 {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        int N = 3;
        Semaphore[] syncs = new Semaphore[N];

        for (int i = 0; i < N; i++) {
            syncs[i] = new Semaphore(1);
            if (i != N - 1) {
                syncs[i].acquire();
            }
        }

        for (int i = 0; i < N; i++) {
            Semaphore lastSync = (i == 0) ? syncs[N - 1] : syncs[i - 1];
            Semaphore currSync = syncs[i];
            final int index = i;
            new Thread(() -> {
                while (count <= 100) {
                    try {
                        lastSync.acquire();
                        System.out.println("Thread" + index + " :" + count++);
                        if (count > 100) {
                            System.exit(0);
                        }
                        currSync.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
