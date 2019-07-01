package com.yuanqinnan.api.thread;

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

    private static void method() {
    }
}
