package com.yuanqin.observer;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/20
 * \* Time: 10:31
 * \* To change this template use File | Settings | File Templates.
 * \* Description:订阅者
 * \
 */
//主题接口
public interface Subject {

    //注册观察者
    void registerObserver(Observer o);

    //移除观察者
    void removeObserver(Observer o);

    //通知观察者
    void notifyObservers();
}
