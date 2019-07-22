package com.yuanqinnan.concurrency.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author yuanqn
 * @date 2019/6/2422:14
 */
public class ThirdThread {

    public static void main(String[] args) {
        //使用lambda表达式
        FutureTask<Integer> task = new FutureTask<>((Callable<Integer>) () -> {
            int i = 0;
            for (; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "的循环变量i的值：" + i);
            }
            return i;
        });
        for (int i = 0; i < 100; i++) {
            //Thread的静态方法currentThread，获取当前线程
            System.out.println(Thread.currentThread().getName());
            if (i == 20) {
                //创建线程并启动
                new Thread(task, "有返回值的线程").start();
            }
        }
        try {
            System.out.println("线程的返回值：" + task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
