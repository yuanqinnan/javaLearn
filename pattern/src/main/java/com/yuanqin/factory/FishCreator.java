package com.yuanqin.factory;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/22
 * \* Time: 15:17
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//创建鱼工厂
public class FishCreator implements Creator {
    public Food CreateFoddFactory() {
        return new Fish();
    }
}
