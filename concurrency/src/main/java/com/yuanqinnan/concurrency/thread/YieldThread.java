package com.yuanqinnan.concurrency.thread;

/**
 * @author:yuanqinnan
 * @date: 2019/6/28 11:18
 */

public class YieldThread extends Thread {

    public YieldThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            //获取当前线程名称
            System.out.println(this.getName() + " " + i);
            if (i == 20) {
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        YieldThread yieldThread = new YieldThread("高级");
        yieldThread.setPriority(Thread.MAX_PRIORITY);
        yieldThread.start();
        YieldThread yieldThread2 = new YieldThread("低级");
        yieldThread2.setPriority(Thread.MIN_PRIORITY);
        yieldThread2.start();

    }
}