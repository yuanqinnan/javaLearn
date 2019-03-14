package com.yuanqinnan.ioc;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/9 22:58
 */
@Component("benzCar")

public class BenzCar implements ICar {
    @Override
    public String getCarName() {
        return "奔驰";
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化方法");
    }
    @PreDestroy
    public void destory(){
        System.out.println("销毁方法");
    }
}
