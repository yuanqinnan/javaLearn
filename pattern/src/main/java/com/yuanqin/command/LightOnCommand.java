package com.yuanqin.command;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/25
 * \* Time: 15:06
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//绑定命令与接收者关系ConreteCommand
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    //具体命令方法
    public void execute() {
        light.on();
    }
}
