package com.yuanqinnan.api.thread;

/**
 * @author:yuanqinnan
 * @date: 2019/7/9 19:17
 */
public class VolatileTest {
    private static  boolean isOver = false;
    private static int a = 1;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isOver) {
                    a++;
                }

            }
        });
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isOver = true;
    }
}