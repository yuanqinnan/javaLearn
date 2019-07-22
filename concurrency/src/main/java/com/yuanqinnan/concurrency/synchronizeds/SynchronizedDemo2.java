package com.yuanqinnan.concurrency.synchronizeds;

/**
 * @author:yuanqinnan
 * @date: 2019/7/3 11:08
 */

public class SynchronizedDemo2 {
    private static int count;

    public static synchronized void countAdd() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo2 demo1 = new SynchronizedDemo2();
        SynchronizedDemo2 demo2 = new SynchronizedDemo2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo1.countAdd();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo2.countAdd();

            }
        }).start();
    }
}