package com.yuanqinnan.ioc;


/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/1/23 21:56
 */
public class User {

    private String name;

    private  Integer age;

    private Car car;

    public User() {
    }

    public User(String name, Integer age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }


    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car.toString()+
                '}';
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }
}
