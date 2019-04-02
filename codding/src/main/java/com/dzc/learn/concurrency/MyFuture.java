package com.dzc.learn.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class MyFuture<T> {

    T result;

    private Callable<T> callable;

    private Throwable throwable;

    private CountDownLatch cdl = new CountDownLatch(1);


    public MyFuture(Callable<T> callable) {
        this.callable = callable;
    }

    public T get() throws InterruptedException {
        cdl.await();
        return result;
    }

    public void run() {
        try {
            T t = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            done();
        }
    }

    private void done() {
        cdl.countDown();
    }
}
