package com.yuanqn.concurrency.locks;

/**
 * @author yuanqn
 * @date 2019/8/18 22:13
 */
public class MyThread extends Thread {
    private LockDemo lockDemo;

    public MyThread(LockDemo lockDemo) {
        super();
        this.lockDemo = lockDemo;
    }

    @Override
    public void run() {
        lockDemo.testMethod();
    }

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        MyThread myThread = new MyThread(lockDemo);
        MyThread myThread2 = new MyThread(lockDemo);
        MyThread myThread3 = new MyThread(lockDemo);
        MyThread myThread4 = new MyThread(lockDemo);
        myThread.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
    }
}
