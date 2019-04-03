package com.yuanqinnan.ioc;

import org.springframework.stereotype.Component;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/9 22:59
 */
@Component("bmwCar")
public class BMWCar implements ICar {

    @Override
    public String getCarName() {
        return "宝马";
    }
}
