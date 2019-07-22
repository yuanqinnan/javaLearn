package com.yuanqn.concurrency.base;

/**
 * @author yuanqn
 * @date 2019/6/27 22:45
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {
            System.out.println("i=" + (i + 1));
        }
    }

    public static void main(String[] args) {
//        try {
            MyThread myThread = new MyThread();
            myThread.start();
            //Thread.sleep(2000);
            myThread.interrupt();
            //System.out.println("是否停止1：" + myThread.interrupted());
            //System.out.println("是否停止2：" + myThread.interrupted());


            System.out.println(myThread.isInterrupted());//true
            System.out.println(myThread.isInterrupted());//true

//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Thread.currentThread().interrupt();
        System.out.println("是否停止1？=" + Thread.interrupted());//true，执行方法后清除了标记
        System.out.println("是否停止2？=" + Thread.interrupted());//false

    }
}
