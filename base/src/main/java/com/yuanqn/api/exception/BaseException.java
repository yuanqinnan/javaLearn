package com.yuanqn.api.exception;

import java.io.*;

public class BaseException {
    public static void main(String[] args) throws IOException {
        try {
            Integer a = Integer.parseInt(args[0]);
            Integer b = Integer.parseInt(args[1]);
            Integer c = a / b;
            System.out.println(c);

        } catch (IndexOutOfBoundsException | NumberFormatException
                | ArithmeticException ie) {
            //异常变量默认final，不能重新赋值
            //ie = new ArithmeticException("text");

        } catch (Exception ex) {

            ex.getMessage();
            ex.getStackTrace();
            ex.printStackTrace();
            ex.printStackTrace(new PrintStream("a.txt"));
        }


        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(""))
        ) {
            bufferedReader.read();
        }
    }
}