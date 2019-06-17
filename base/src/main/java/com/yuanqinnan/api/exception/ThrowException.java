package com.yuanqinnan.api.exception;


public class ThrowException {
    public static void main(String[] args) {
        try {
            //检查时异常需要写try catch
            test1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //运行时异常直接调用即可
        test2();

    }

    public static void test1() throws Exception {
        if (1 > 0) {
            throw new Exception("用户不存在");
        }
    }

    public static void test2() {
        if (1 > 0) {
            throw new RuntimeException("用户不存在");
        }
    }

}