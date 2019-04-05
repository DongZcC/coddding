package com.dzc.learn.concurrency;

import java.util.concurrent.*;

public class CountTask extends RecursiveTask<Integer> {


    private int start;

    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < 5) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int mid = (start + end) / 2;
            CountTask leftTask = new CountTask(start, mid - 1);
            CountTask rightTask = new CountTask(mid, end);

            leftTask.fork();
            rightTask.fork();

            sum += leftTask.join();
            sum += rightTask.join();
        }
        return sum;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        System.out.println("Poll init: " + forkJoinPool);

        ForkJoinTask<Integer> task = forkJoinPool.submit(new CountTask(1, 100));

        System.out.println("total : " + task.get());

        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);

        forkJoinPool.shutdown();
    }
}
