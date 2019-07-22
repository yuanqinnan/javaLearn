package com.yuanqn.concurrency.synchronizeds;

/**
 * @author:yuanqinnan
 * @date: 2019/7/3 9:41
 */


public class SynchronizedDemo1 {

    private int count;

    public synchronized void countAdd() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void print() {
        System.out.println(Thread.currentThread().getName() + "的打印方法:" + count);
    }

    public static void main(String[] args) {
        SynchronizedDemo1 demo1 = new SynchronizedDemo1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo1.countAdd();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo1.print();
                demo1.countAdd();

            }
        }).start();
    }
}