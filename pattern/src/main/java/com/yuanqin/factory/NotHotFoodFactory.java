package com.yuanqin.factory;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/22
 * \* Time: 17:03
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//不辣的的工厂
public class NotHotFoodFactory implements FoodFactory {

    public Meat createMeat() {
        return new NotHotMeat();
    }

    public Fish createFish() {
        return new NotHotFish();
    }
}
