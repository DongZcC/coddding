package com.dzc.learn.concurrency;

import java.util.concurrent.*;

public class InterruptByFuture {

    public static void main(String[] args) throws ExecutionException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<?> future = es.submit(new MyThread());


        try {
            future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw e;
        } catch (TimeoutException e) {
            System.out.println("thread over time");
        } finally {
            future.cancel(true);
        }
    }


    private static class MyThread extends Thread {


        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("count");

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupt");
                    Thread.currentThread().interrupt();
                }

            }
            System.out.println("Thread stop");
        }


        public void cancel() {
            interrupt();
        }
    }

}
