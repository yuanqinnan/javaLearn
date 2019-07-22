package com.yuanqinnan.concurrency.synchronizeds;


/**
 * @author:yuanqinnan
 * @date: 2019/7/3 11:25
 */

public class SynchronizedDemo3 {

    private int count;

    public void countAdd() {
        synchronized (SynchronizedDemo3.class) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo3 demo1 = new SynchronizedDemo3();
        SynchronizedDemo3 demo3 = new SynchronizedDemo3();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo1.countAdd();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo3.countAdd();

            }
        }).start();
    }
}