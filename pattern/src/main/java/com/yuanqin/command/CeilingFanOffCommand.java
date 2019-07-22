package com.yuanqin.command;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqn
 * \* Date: 2018/12/25
 * \* Time: 15:51
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//吊灯的开灯命令
public class CeilingFanOffCommand implements Command {
    CeilingFan ceilingFan;

    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    //具体命令方法
    public void execute() {
        ceilingFan.off();
    }
}
