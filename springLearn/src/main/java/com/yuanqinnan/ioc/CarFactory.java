package com.yuanqinnan.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/9 23:04
 */
@Service("cf")
public class CarFactory {

    @Autowired
    @Qualifier("bmwCar")
    private ICar iCar;

//    @Resource(type = BenzCar.class)
//    private ICar iCar2;
//
//    @Resource(name = "benzCar")
//    private ICar iCar3;

    public String toString(){
        System.out.println(iCar.getCarName());
        return iCar.getCarName();
    }
}
