package com.dzc.learn.basic;

import java.util.concurrent.atomic.AtomicInteger;

public class BitOptions {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;


    // Packing and unpacking ctl
    private static int runStateOf(int c) {
        return c & ~CAPACITY;
    }

    private static int workerCountOf(int c) {
        return c & CAPACITY;
    }

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }


    public static void main(String[] args) {
        BitOptions ops = new BitOptions();
        System.out.println(Integer.toBinaryString(ops.ctl.intValue()));
        System.out.println("Running : " + Integer.toBinaryString(RUNNING));
        System.out.println("-1 : " + Integer.toBinaryString(-1));
        System.out.println("CAPACITY : " + Integer.toBinaryString(CAPACITY));
        System.out.println("CAPACITY.LENGTH : " + Integer.toBinaryString(CAPACITY).length());
    }
}
