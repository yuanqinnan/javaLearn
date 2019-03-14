package com.yuanqin.factory;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/22
 * \* Time: 10:44
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//具体食物 鱼
public class Fish implements Food {
    public void prepare() {
        System.out.println("洗鱼");
    }
    public void cook() {
        System.out.println("水煮鱼");
    }
    public void box() {
        System.out.println("大碗装鱼");
    }
}
