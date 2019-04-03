package com.yuanqinnan.aop;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/10 18:16
 */
//员工类
public class Employee {
    private Integer uid;

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {

        return uid;
    }

    private Integer age;

    private String name;

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
