package com.yuanqin.singleton;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/24
 * \* Time: 10:00
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//单例模式实现-懒汉式
public class Singleton {

    //利用静态变量记录类的唯一实例
    private static Singleton uniqueInstance;

    //把构造器设置为私有，只有内部才可以调用构造器
    private Singleton() {
    }

    //用方法实例化对象
    public static synchronized Singleton getInstance() {
        //如果为空则说明还没有创建实例，这个时候我们可以创建，这个就是延时实例化，也就是传说中的懒汉式
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

    public void showMsg() {
        System.out.println("其他方法，显示数据");
    }
}
