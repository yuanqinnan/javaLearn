package com.yuanqinnan.api.thread;

/**
 * @author yuanqn
 * @date 2019/6/2322:27
 */
public class FirstThread extends Thread {

    private int i=0;
    public void run() {
        for (; i < 100; i++) {
            //获取当前线程名称
            System.out.println(this.getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //Thread的静态方法currentThread，获取当前线程
            System.out.println(Thread.currentThread().getName());
            if (i == 20) {
                //创建线程并启动
                new FirstThread().start();
                new FirstThread().start();
            }

        }
    }
}
