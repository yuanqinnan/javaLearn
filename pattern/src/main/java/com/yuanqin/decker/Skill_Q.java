package com.yuanqin.decker;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqn
 * \* Date: 2018/12/21
 * \* Time: 11:28
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//具体装饰子类，用来装饰
public class Skill_Q extends Skills {
    private String skillName;

    public Skill_Q(Hero hero, String skillName) {
        super(hero);
        this.skillName = skillName;
    }

    @Override
    public void learnSkills() {
        System.out.println("学习了技能Q:" + skillName);
        super.learnSkills();
    }
}
