package com.yuanqinnan.api.io;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {


    private  int age;

    private String name;

    public Person(String name, int age) {
        System.out.println("有参数的构造器");
        this.age = age;
        this.name = name;
    }
}