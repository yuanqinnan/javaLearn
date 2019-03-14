package com.yuanqinnan.ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/1/23 22:02
 */
@Component("c")
@Scope(scopeName = "prototype")
public class Car {

    private String Color;

    public String getColor() {
        return Color;
    }
    @Value("yellow")
    public void setColor(String color) {
        Color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Color='" + Color + '\'' +
                '}';
    }
}
