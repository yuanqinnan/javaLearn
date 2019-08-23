package com.yuanqn.concurrency.locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:yuanqinnan
 * @date: 2019/8/22 14:01
 */
public class LockDemo2 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
    }
}