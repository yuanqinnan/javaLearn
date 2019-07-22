package com.yuanqn.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/10 20:04
 */
public class ObjectInterceptor implements InvocationHandler {

    //目标类
    private Object target;
    //切面类（这里指事务类）
    private MyTransaction transaction;

    //通过构造器赋值
    public ObjectInterceptor(Object target, MyTransaction transaction) {
        this.target = target;
        this.transaction = transaction;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        this.transaction.before();
        method.invoke(target, args);
        this.transaction.after();
        return null;
    }
}
