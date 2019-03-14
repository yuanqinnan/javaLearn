package com.yuanqin.singleton;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/24
 * \* Time: 10:48
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//单例模式-饿汉式
public class Singleton1 {
    //初始化器重直接创建，可以保证线程安全
    private static Singleton1 uniqueInstance=new Singleton1();
    //静态方法
    private Singleton1(){}
    //直接返回
    public static Singleton1 getInstance(){
        return uniqueInstance;
    }
}
