package com.yuanqinnan;

import com.yuanqinnan.aop.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/10 19:47
 */
public class AopTest {

    @Test
    public void fun1(){
        MyTransaction transaction = new MyTransaction();
        EmployeeService EmployeeService = new EmployeeServiceImpl();
        //产生静态代理对象
        ProxyEmployee proxy = new ProxyEmployee(EmployeeService, transaction);
        proxy.addEmployee(null);
        proxy.deleteEmployee(0);
    }

    @Test
    public void fun2(){
        //目标类
        Object target = new EmployeeServiceImpl();
        //事务类
        MyTransaction transaction = new MyTransaction();
        ObjectInterceptor proxyObject = new ObjectInterceptor(target, transaction);
        /**
         * 三个参数的含义：
         * 1、目标类的类加载器
         * 2、目标类所有实现的接口
         * 3、拦截器
         */
        EmployeeService employeeService = (EmployeeService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), proxyObject);
        employeeService.addEmployee(null);
        employeeService.deleteEmployee(0);
    }

    @Test
    public void fun3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext3.xml");
        EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
        employeeService.addEmployee(null);
    }

    @Test
    public void fun4(){
        ApplicationContext act =  new ClassPathXmlApplicationContext("/applicationContext4.xml");
        EmployeeService employeeService = (EmployeeService) act.getBean("employeeService");
        employeeService.addEmployee(null);
    }
}
