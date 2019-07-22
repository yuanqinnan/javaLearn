package com.yuanqn.api.io;

import java.io.*;

/**
 * @author yuanqn
 * @date 2019/6/1522:32
 */
public class PushTest {

    public static void main(String[] args){
        try {
            PushbackReader pushbackReader=new PushbackReader(new FileReader(""),64);
            char[] buffer=new char[32];

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
