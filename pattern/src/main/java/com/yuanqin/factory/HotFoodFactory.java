package com.yuanqin.factory;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/22
 * \* Time: 17:01
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//制造辣的工厂
public class HotFoodFactory implements FoodFactory {
    public Meat createMeat() {
        return new HotMeat();
    }
    public Fish createFish() {
         return new HotFish();
    }
}
