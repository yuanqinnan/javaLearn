package com.yuanqin.observer;

import java.util.ArrayList;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/20
 * \* Time: 10:51
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//主题实现
public class WeatherData implements Subject {
    //订阅者列表
    private ArrayList observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(){
        observers=new ArrayList();
    }
    //增加订阅者
    public void registerObserver(Observer o) {
        observers.add(o);
    }
    //移除订阅者
    public void removeObserver(Observer o) {
        int index=observers.indexOf(o);
        if(index>=0) {
            observers.remove(index);
        }
    }
    //通知订阅者
    public void notifyObservers() {
        for (int i = 0; i <observers.size() ; i++) {
            Observer observer = (Observer)observers.get(i);
            observer.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
