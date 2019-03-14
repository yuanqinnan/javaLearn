package com.yuanqinnan.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/9 22:40
 */
//自身也注入其中
@Component
public class Boss {
    //自动装配
    @Autowired
    private Car car;

    private String name;
    @Value("袁帅")
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car.getColor() +
                ", name='" + name + '\'' +
                '}';
    }
}
