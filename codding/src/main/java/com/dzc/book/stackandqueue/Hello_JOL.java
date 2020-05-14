package com.dzc.book.stackandqueue;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author Administrator
 * @date 2020-05-14 18:27
 */
public class Hello_JOL {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        Object o = new Object();

        System.out.println(ClassLayout.parseInstance(o).toPrintable());


        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

    }
}
