package com.yuanqin.factory;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/22
 * \* Time: 16:40
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//抽象工厂
public interface FoodFactory {
    //创建肉工厂
    Meat createMeat();
    //创建鱼工厂
    Fish createFish();
}
