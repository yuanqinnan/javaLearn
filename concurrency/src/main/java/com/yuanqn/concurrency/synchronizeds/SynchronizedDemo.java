package com.yuanqn.concurrency.synchronizeds;

/**
 * @author yuanqn
 * @date 2019/7/1 22:45
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
        synchronized (SynchronizedDemo.class) {
        }
        method();
    }

    private synchronized static void method() {
    }
}
