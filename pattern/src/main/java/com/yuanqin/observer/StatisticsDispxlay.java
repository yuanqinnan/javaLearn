package com.yuanqin.observer;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/20
 * \* Time: 11:37
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class StatisticsDispxlay implements DisplayElement, Observer {

    private float temperature;
    private float pressure;
    private Subject weatherData;

    public StatisticsDispxlay(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public void display() {
        System.out.println("第二种显示方式: " + temperature
                + "F degrees and " + pressure + "% pressure");
    }

    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.pressure = pressure;
    }
}
