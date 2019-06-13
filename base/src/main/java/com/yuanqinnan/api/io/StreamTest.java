package com.yuanqinnan.api.io;


import java.io.*;

public class StreamTest {

    public static void main(String[] str) throws IOException {
        //inputTest();
        //先将System.in转换成Reader 对象
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        //再将Reader包装成BufferedReader
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals("exit")) {
                System.exit(1);
            }
            System.out.println("输入的内容是：" + line);
        }


    }

    private static void inputTest() throws IOException {
        InputStream inputStream = new FileInputStream("StreamTest.java");
        byte[] bytes = new byte[1024];
        int hasRead = 0;
        while ((hasRead = inputStream.read(bytes)) > 0) {
            System.out.println(new String(bytes, 0, hasRead));
        }
        inputStream.close();
    }
}