package com.yuanqinnan.api.io;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTest {

    public static void main(String[] str) throws IOException {
        InputStream inputStream = new FileInputStream("StreamTest.java");
        byte[] bytes = new byte[1024];
        int hasRead = 0;
        while ((hasRead = inputStream.read(bytes)) > 0) {
            System.out.println(new String(bytes, 0, hasRead));
        }

        inputStream.close();
    }
}