package com.yuanqin.decker;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqn
 * \* Date: 2018/12/21
 * \* Time: 11:11
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//具体英雄兰博，需要被扩展的类
public class Lanbo extends Hero {
    //英雄属性
    private String name;

    public Lanbo(String name) {
        this.name = name;
    }

    @Override
    public void learnSkills() {
        System.out.println(name + "学习了以上技能！");
    }
}
