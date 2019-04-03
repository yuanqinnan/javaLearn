package com.yuanqin.factory;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/22
 * \* Time: 10:43
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class SimpleFoodFactory {
    //静态创建工厂方法
    public static Food createFood(String type) {
        if (!type.isEmpty()) {
            switch (type) {
                case "FISH":
                    return new Fish();
                case "MEAT":
                    return new Meat();
                default:
                    return null;
            }
        }
        return null;
    }
}
