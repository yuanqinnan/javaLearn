package com.yuanqin.strategy;

public abstract class Duck {

    //飞行接口
    Flybehavior flybehavior;
    //叫声接口
    QuackBehavior quackBehavior;

    public Duck() {

    }

    //叫声方法
    public void performquack() {
        quackBehavior.quack();
    }

    //飞行方法
    public void performFly() {
        flybehavior.fly();
    }

    public abstract void display();

    public void swim() {
        System.out.println("All ducks float, even decoys!");
    }
}
