package com.yuanqin.singleton;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqn
 * \* Date: 2018/12/24
 * \* Time: 11:01
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//双重加锁方式
public class Singleton2 {

    //利用volatile 关键字保证多个线程正确使用uniqueInstance
    private volatile static Singleton2 uniqueInstance;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        //检查实例，不存在则进入同步代码块
        if (uniqueInstance == null) {
            //同步代码块
            synchronized (Singleton2.class) {
                //进入代码块后，再判断一次
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton2();
                }
            }
        }
        return uniqueInstance;
    }
}
