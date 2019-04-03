package com.yuanqin.decker;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/21
 * \* Time: 11:32
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//具体装饰子类，用来装饰E技能
public class Skill_E extends Skills {
    private String skillName;

    public Skill_E(Hero hero, String skillName) {
        super(hero);
        this.skillName = skillName;
    }

    @Override
    public void learnSkills() {
        System.out.println("学习了技能E:" + skillName);
        super.learnSkills();
    }
}
