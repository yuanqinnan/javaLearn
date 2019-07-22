package com.yuanqin.command;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqn
 * \* Date: 2018/12/25
 * \* Time: 15:53
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//遥控调用
public class RemoteControl {
    //申明命令数组
    Command[] onCommands;
    Command[] offCommands;

    public RemoteControl() {
        onCommands = new Command[4];
        offCommands = new Command[4];
    }

    //设置命令
    public void setCommand(int solt, Command onCommand, Command offCommand) {
        onCommands[solt] = onCommand;
        offCommands[solt] = offCommand;
    }

    //打开按钮
    public void onButtonWasPressed(int solt) {
        onCommands[solt].execute();
    }

    //关闭按钮
    public void offButtonWasPressed(int solt) {
        offCommands[solt].execute();
    }

}
