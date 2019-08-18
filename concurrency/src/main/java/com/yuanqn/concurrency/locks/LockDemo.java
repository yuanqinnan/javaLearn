package com.yuanqn.concurrency.locks;

import lombok.Data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuanqn
 * @date 2019/8/18 22:04
 */
@Data
public class LockDemo {
    private Lock lock = new ReentrantLock();

    public void testMethod() {
        lock.lock();

        for (int i = 0; i < 5; i++) {
            System.out.println("线程名：" + Thread.currentThread().getName() + " " + (i + 1));
        }
        lock.unlock();

    }

}
