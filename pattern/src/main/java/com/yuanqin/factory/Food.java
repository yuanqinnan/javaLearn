package com.yuanqin.factory;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/22
 * \* Time: 10:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//食物接口
public interface Food {
    //准备
    void prepare();

    //烹饪
    void cook();

    //打包
    void box();
}
