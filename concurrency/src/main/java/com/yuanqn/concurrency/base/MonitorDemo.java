package com.yuanqn.concurrency.base;

/**
 * @author:yuanqinnan
 * @date: 2019/7/2 11:15
 */


public class MonitorDemo {
    private int a = 0;

    public synchronized void writer() {     // 1
        a++;                                // 2
    }                                       // 3

    public synchronized void reader() {    // 4
        int i = a;                         // 5
    }
}