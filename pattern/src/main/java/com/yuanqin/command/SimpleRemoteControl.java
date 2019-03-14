package com.yuanqin.command;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/25
 * \* Time: 15:14
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//命令模式的客户（Invoker）
public class SimpleRemoteControl {
    //命令接口
    Command solt;
    public void setCommand(Command command){
        this.solt=command;
    }
    //命令方法
    public void buttonWasPressed(){
        solt.execute();
    }

}
