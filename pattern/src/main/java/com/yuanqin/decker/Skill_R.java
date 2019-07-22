package com.yuanqin.decker;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqn
 * \* Date: 2018/12/21
 * \* Time: 11:34
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//具体装饰子类，用来装饰R技能
public class Skill_R extends Skills {
    private String skillName;

    public Skill_R(Hero hero, String skillName) {
        super(hero);
        this.skillName = skillName;
    }

    @Override
    public void learnSkills() {
        System.out.println("学习了技能R:" + skillName);
        super.learnSkills();
    }
}
