package com.yuanqin.factory;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/22
 * \* Time: 15:18
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//创建肉工厂
public class MeatCreator implements Creator {

    public Food CreateFoddFactory() {
        return new Meat();
    }
}
