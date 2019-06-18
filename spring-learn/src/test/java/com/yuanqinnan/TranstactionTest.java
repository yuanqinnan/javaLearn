package com.yuanqinnan;

import com.yuanqinnan.transaction.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2019/3/12
 * \* Time: 9:09
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TranstactionTest {

    @Test
    public void fun1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext5.xml");
        AccountService account = (AccountService) context.getBean("accountService");
        //张三 向 李四 转账1000
        account.transfer("zhangsan", "lisi", 1000d);
    }

    @Test
    public void fun2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext6.xml");
        AccountService account = (AccountService) context.getBean("accountService");
        //张三 向 李四 转账1000
        account.transfer("zhangsan", "lisi", 1000d);
    }

    @Test
    public void fun3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext7.xml");
        AccountService account = (AccountService) context.getBean("accountService");
        //张三 向 李四 转账1000
        account.transfer("zhangsan", "lisi", 1000d);
    }

    @Test
    public void fun4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext8.xml");
        AccountService account = (AccountService) context.getBean("accountService");
        //张三 向 李四 转账1000
        account.transfer("zhangsan", "lisi", 1000d);
    }
}
