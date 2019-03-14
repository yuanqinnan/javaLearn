package com.yuanqin.observer;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/20
 * \* Time: 11:26
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class CurrentConditionsDisplay implements DisplayElement,Observer {

    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void display() {
        System.out.println("第一种显示方式: " + temperature
                + "F degrees and " + humidity + "% humidity");
    }

    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }
}
