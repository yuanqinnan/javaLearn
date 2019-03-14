package com.yuanqinnan;

import static org.junit.Assert.assertTrue;

import com.yuanqinnan.ioc.Boss;
import com.yuanqinnan.ioc.Car;
import com.yuanqinnan.ioc.HelloWorld;
import com.yuanqinnan.ioc.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void fun1(){
        //读取配置文件
        ApplicationContext ctx=new ClassPathXmlApplicationContext("/applicationContext.xml");
        //获取bean的实例
        HelloWorld t=(HelloWorld) ctx.getBean("hello");
        //调用方法
        System.out.println(t.getInfo());
    }

    @Test
    public void fun2(){
        //读取配置文件
        ApplicationContext ctx=new ClassPathXmlApplicationContext("/applicationContext.xml");
        //获取bean的实例
        User t=(User) ctx.getBean("user");
        //调用方法
        System.out.println(t.toString());
    }
    @Test
    public void fun3(){
        //读取配置文件
        ApplicationContext ctx=new ClassPathXmlApplicationContext("/applicationContext.xml");
        //获取bean的实例
        User t=(User) ctx.getBean("user2");
        //调用方法
        System.out.println(t.toString());
    }
    @Test
    public void fun4(){
        //读取配置文件
        ApplicationContext ctx=new ClassPathXmlApplicationContext("/applicationContext.xml");
        //获取bean的实例
        User t=(User) ctx.getBean("user3");
        //调用方法
        System.out.println(t.toString());
    }

    @Test
    public void fun5(){
        //读取配置文件
        ApplicationContext ctx=new ClassPathXmlApplicationContext("/applicationContext2.xml");
        //获取bean的实例
        Car car=(Car) ctx.getBean("c");
        System.out.println(car.toString());
    }
    @Test
    public void fun6(){
        //读取配置文件
        ApplicationContext ctx=new ClassPathXmlApplicationContext("/applicationContext2.xml");
        //获取bean的实例
        Boss boss=(Boss)ctx.getBean("boss");
        System.out.println(boss.toString());
    }
}
