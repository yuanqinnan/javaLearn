package com.yuanqn.concurrency.synchronizeds;

/**
 * @author yuanqn
 * @date 2019/7/9 22:46
 */
public class Singleton {
    private volatile static Singleton instance;

    private Singleton() {
    }

    public Singleton getInstance() {
        if (instance == null) {//步骤1
            synchronized (Singleton.class) {//步骤2
                if (instance == null) {//步骤3
                    instance = new Singleton();//步骤4
                }
            }
        }
        return instance;
    }

}
