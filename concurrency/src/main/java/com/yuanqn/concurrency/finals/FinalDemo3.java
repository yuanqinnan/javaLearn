package com.yuanqn.concurrency.finals;

/**
 * @author:yuanqinnan
 * @date: 2019/7/23 10:10
 */
public class FinalDemo3 {
    private int i;// 普通变量
    private final int j;// final变量
    private static FinalDemo3 obj;

    public FinalDemo3() { // 构造函数
        i = 1; // 写普通域
        j = 2;// 写final域
    }

    public static void writer() {// 写线程A执行
        obj = new FinalDemo3();
    }

    public static void reader() {// 读线程B执行
        FinalDemo3 object = obj; // 读对象引用
        int a = object.i; // 读普通域
        int b = object.j; // 读final域
    }
}