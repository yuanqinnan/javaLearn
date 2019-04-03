package com.yuanqin.decker;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/21
 * \* Time: 11:30
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//具体装饰子类，用来装饰 W技能
public class Skill_W extends Skills {
    private String skillName;

    public Skill_W(Hero hero, String skillName) {
        super(hero);
        this.skillName = skillName;
    }

    @Override
    public void learnSkills() {
        System.out.println("学习了技能W:" + skillName);
        super.learnSkills();
    }
}
