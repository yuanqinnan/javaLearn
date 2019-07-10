package com.yuanqinnan.api.thread;

/**
 * @author:yuanqinnan
 * @date: 2019/7/10 10:09
 */
public class VolatileExample2 {
    private int a = 0;
    private boolean flag = false;

    public void writer() {
        a = 1;          //1
        flag = true;   //2
    }

    public void reader() {
        if (flag) {      //3
            int i = a; //4
        }
    }

    public static void main(String[] args) {
        VolatileExample2 volatileExample2 = new VolatileExample2();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileExample2.writer();

            }
        });
        thread.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileExample2.reader();

            }
        });
        thread2.start();

    }
}