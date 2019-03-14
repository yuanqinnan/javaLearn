package com.yuanqin.observer;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/20
 * \* Time: 11:48
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ForecastDisplay implements DisplayElement,Observer {
    private  float humidity;
    private  float pressure;
    private WeatherData weatherData;

    public ForecastDisplay(WeatherData weatherData){
        this.weatherData=weatherData;
    }

    public void display() {
        System.out.println("第三种显示方式: " + humidity
                + "F degrees and " + pressure + "% pressure");
    }

    public void update(float temp, float humidity, float pressure) {
        this.humidity=humidity;
        this.pressure=pressure;
    }
}
