package com.yuanqinnan.concurrency.thread;

/**
 * @author yuanqn
 * @date 2019/6/23 23:32
 */
public class SecondThread implements Runnable {
    private int i = 0;

    @Override
    public void run() {
        for (; i < 100; i++) {
            //此时想要获取到多线程对象，只能使用Thread.currentThread()方法
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //Thread的静态方法currentThread，获取当前线程
            System.out.println(Thread.currentThread().getName());
            if (i == 20) {
                //创建线程并启动
                SecondThread secondThread=new SecondThread();
                new Thread(secondThread,"线程一").start();
                new Thread(secondThread,"线程二").start();
            }

        }
    }
}
