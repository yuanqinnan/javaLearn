package com.yuanqinnan.api.thread;

/**
 * @author:yuanqinnan
 * @date: 2019/6/28 13:58
 */

public class DaemonThread extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("i am alive");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("finally block");
            }
        }
    }
    public static void main(String[] args) {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
        //确保main线程结束前能给daemonThread能够分到时间片
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}