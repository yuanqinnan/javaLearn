package com.yuanqin.decker;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/21
 * \* Time: 11:16
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//抽象技能类，装饰者的抽象基类
public abstract class Skills extends Hero {
    private Hero hero;

    public Skills(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void learnSkills() {
        if (hero != null) {
            hero.learnSkills();
        }
    }
}
