package com.yuanqin.command;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqn
 * \* Date: 2018/12/25
 * \* Time: 15:48
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    //具体命令方法
    public void execute() {
        light.off();
    }
}
