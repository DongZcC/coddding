package com.dzc.learn.concurrency;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class CompletableFutureExample {


    // 1. Creating a completed CompletableFuture
    static void completedFutureExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
    }


    // 2. Running a simple asynchronous stage
    static void runAsyncExample() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
        });
        assertFalse(cf.isDone());
        sleepEnough();
        assertTrue(cf.isDone());
    }

    private static void randomSleep() {
        Random r = new Random(System.currentTimeMillis());
        int t = r.nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleepEnough() {

    }


    // 3. Applying a Function on previous stage
    static void thenApplyExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message")
                .thenApply(s -> {   // 阻塞方法 The execution of the Function will be blocking
                    assertFalse(Thread.currentThread().isDaemon());
                    return s.toUpperCase();
                });
        assertEquals("MESSAGE", cf.getNow(null));
    }


    // 4. Asynchronously applying a Function on previous stage
    static void thenApplyAsyncExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").
                thenApplyAsync(s -> {  // asynchronously another thread
                    assertTrue(Thread.currentThread().isDaemon());
                    randomSleep();
                    return s.toUpperCase();
                });
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }

    // 5. Asynchronously applying a Function on previous stage using a custom Executor
    static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;
        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    static void thenApplyAsyncWithExecutorExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
            assertFalse(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        }, executor);
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }


    @Test
    public void test() {
        thenApplyExample();
    }
}
