package com.yuanqin.factory;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/22
 * \* Time: 10:47
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//具体食物类 肉
public class Meat implements Food {
    public void prepare() {
        System.out.println("切肉");
    }
    public void cook() {
        System.out.println("红烧肉");
    }
    public void box() {
        System.out.println("装肉");
    }
}
