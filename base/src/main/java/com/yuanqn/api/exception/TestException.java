package com.yuanqn.api.exception;

/**
 * @author:yuanqinnan
 * @date: 2019/7/9 14:31
 */

public class TestException {

    public static void main(String[] args) {
        String msg = test();
        System.out.println(msg);
    }

    public static String test() {
        try {
            return "try中的返回值";
        } catch (Exception ex) {
            return "catch中的返回值";
        } finally {
            return "finally的返回值";
        }

    }
}