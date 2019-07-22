package com.yuanqin.factory;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqn
 * \* Date: 2018/12/22
 * \* Time: 17:00
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//不辣的肉
public class NotHotMeat extends Meat {

    @Override
    public void cook() {
        System.out.println("红烧不辣的肉");
    }
}
