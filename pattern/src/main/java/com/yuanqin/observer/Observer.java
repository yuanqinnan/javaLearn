package com.yuanqin.observer;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/20
 * \* Time: 10:29
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
// 观察者接口
public interface Observer {
    public void update(float temp, float humidity, float pressure);
}
