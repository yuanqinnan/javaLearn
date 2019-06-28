package com.yuanqinnan.api.thread;

/**
 * @author:yuanqinnan
 * @date: 2019/6/28 10:16
 */
public class JoinThread extends Thread {

    private Thread thread;

    public JoinThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            thread.join();
            for (int i = 0; i < 10; i++) {
                System.out.println(thread.getName() + "的执行 " + i);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 1; i <= 10; i++) {
            Thread curThread = new JoinThread(previousThread);
            curThread.start();
            previousThread = curThread;
        }

    }
}