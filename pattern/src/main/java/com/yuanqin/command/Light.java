package com.yuanqin.command;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/25
 * \* Time: 15:09
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//命令接收者(Receiver),电灯
public class Light {
    private String name;

    public Light(String name) {
        this.name = name;
    }

    //开灯操作
    public void on() {
        System.out.println(name + ":开灯！");
    }

    //关灯操作
    public void off() {
        System.out.println(name + "：关灯");
    }
}
