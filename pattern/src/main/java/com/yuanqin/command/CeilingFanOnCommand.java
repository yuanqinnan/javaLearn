package com.yuanqin.command;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/25
 * \* Time: 15:50
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//吊灯的关灯命令
public class CeilingFanOnCommand implements Command {
    CeilingFan ceilingFan;
    public CeilingFanOnCommand(CeilingFan ceilingFan){
        this.ceilingFan = ceilingFan;
    }
    //具体命令方法
    public void execute() {
        ceilingFan.on();
    }
}
