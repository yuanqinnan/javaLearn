package com.yuanqn.concurrency.finals;

/**
 * @author:yuanqinnan
 * @date: 2019/7/10 14:43
 */
public class FinalDemo {

    private final int a = 6;

    private final String str;

    private final static boolean b;

    private final double c;

    //final变量未初始化时系统不会进行隐式初始化，会出现报错
    //private final char aChar;

    {
        str = "初始化代码块赋值";
    }

    static {

        //静态变量在静态块中进行了赋值，
        b = true;
        //非静态变量不能在静态块中赋值
        //c=100;
    }

    public FinalDemo() {
        //实例变量
        c = 1.0;
        //已经赋值的不能修改
        //a = 10;
    }

    public void a(final int f) {
        //实例方法不能为final类型变量赋值
        //a = 10;
        final int d;
        //调用方法入参一定会赋值，此时再次赋值就会出错
        //f=2;
        //未赋值可以进行赋值
        d=2;
        //已赋值过一次，不允许再次赋值
        //d=3;
    }
}