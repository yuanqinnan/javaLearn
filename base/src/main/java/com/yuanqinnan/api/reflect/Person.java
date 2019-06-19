package com.yuanqinnan.api.reflect;

import java.lang.reflect.Field;

public class Person {

    private int age;
    private String name;

    public String toString() {
        return name + ":" + age;
    }

    public static void main(String[] args) throws Exception {
        Person p = new Person();
        Class<Person> personClass = Person.class;
        Field name = personClass.getDeclaredField("name");
        //去掉访问限制
        name.setAccessible(true);
        name.set(p, "张三");

        Field age = personClass.getDeclaredField("age");
        age.setAccessible(true);
        age.set(p, 20);
        System.out.println(p.toString());
    }
}
