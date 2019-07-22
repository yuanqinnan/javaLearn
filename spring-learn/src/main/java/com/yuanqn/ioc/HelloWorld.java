package com.yuanqn.ioc;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/1/15 23:30
 */
public class HelloWorld {

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void destory() {
        System.out.println("销毁方法");
    }
}
