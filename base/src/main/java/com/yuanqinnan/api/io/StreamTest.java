package com.yuanqinnan.api.io;


import java.io.*;

public class StreamTest {

    public static void main(String[] str) throws IOException {
        //input();
        FileWriter fileWriter = new FileWriter("test.txt");
        fileWriter.write("日照香炉生紫烟\r\n");
        fileWriter.write("遥看瀑布挂前川\r\n");
        fileWriter.write("飞流直下三千尺\r\n");
        fileWriter.write("遥看瀑布挂前川\r\n");
        fileWriter.close();

    }

    public static void input() throws IOException {
        InputStream inputStream = new FileInputStream("StreamTest.java");
        byte[] bytes = new byte[1024];
        int hasRead = 0;
        while ((hasRead = inputStream.read(bytes)) > 0) {
            System.out.println(new String(bytes, 0, hasRead));
        }

        inputStream.close();
    }
}