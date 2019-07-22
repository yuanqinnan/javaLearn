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

    //private final char aChar;

    {
        str = "初始化代码块赋值";
    }

    static {

        b = true;
        //c=100;
    }

    public FinalDemo() {
        c = 1.0;
        //a = 10;已经赋值的不能修改
    }

    public void a() {
        //a = 10;实例方法不能为final类型变量赋值
    }
}