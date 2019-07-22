package com.yuanqn.api.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassTest {

    private ClassTest() {
    }

    public ClassTest(String name) {
        System.out.println("有参数的构造函数：" + name);
    }

    public void info() {
        System.out.println("无参数的方法");
    }

    public void info(String name) {
        System.out.println("有参数的方法:" + name);
    }

    //内部类
    class inner {
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException {
        Class<ClassTest> clazz = ClassTest.class;
        ClassTest classTest = clazz.newInstance();
        classTest.info();
        Constructor<ClassTest> constructor = clazz.getConstructor(String.class);
        ClassTest class2 = constructor.newInstance("你好");
        class2.info();
        Class<?> aClass = Class.forName("com.yuanqn.api.reflect.ClassTest");
        //创建了对象
        Object object = aClass.newInstance();
        //获取到方法
        Method info = aClass.getMethod("info", String.class);
        //调用方法
        info.invoke(object, "你好");


//        Constructor<?>[] constructors = clazz.getConstructors();
//        System.out.println("全部public构造器如下：");
//        for (Constructor constructor : constructors) {
//            System.out.println(constructor);
//        }
//        Constructor<?>[] pubConstructors = clazz.getDeclaredConstructors();
//        System.out.println("全部构造器如下：");
//        for (Constructor constructor : pubConstructors) {
//            System.out.println(constructor);
//        }
//        Method[] methods = clazz.getMethods();
//        System.out.println("全部public方法如下");
//        for (Method method : methods) {
//            System.out.println(method);
//        }
//        System.out.println("名称为info,并且入参为String类型的方法：" + clazz.getMethod("info", String.class));

    }
}