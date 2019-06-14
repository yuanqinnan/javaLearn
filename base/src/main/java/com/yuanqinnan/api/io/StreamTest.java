package com.yuanqinnan.api.io;


import java.io.*;

public class StreamTest {

    public static void main(String[] str) throws IOException {
        //input();
        //fileWriter();
        //先将System.in转换成Reader 对象
        //streamReader();
        PushbackReader pushbackReader=new PushbackReader(new FileReader("io/"),64);

    }

    private static void streamReader() throws IOException {
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

    private static void fileWriter() throws IOException {
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