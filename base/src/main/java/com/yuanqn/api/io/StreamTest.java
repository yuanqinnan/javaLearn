package com.yuanqn.api.io;


import java.io.*;

public class StreamTest {

    public static void main(String[] str) throws IOException {
        //input();
        //fileWriter();

        //streamReader();

        //pushReader();

        //random();
        //创建输出流
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("object.txt"));
        Person person = new Person("张三", 10);
        //将person写入文件中
        objectOutputStream.writeObject(person);
        //创建输入流
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("object.txt"));
        try {
            //读出数据
            Person p = (Person) objectInputStream.readObject();
            System.out.println(p);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return;

    }

    private static void random() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("StreamTest.java", "r");
        System.out.println("文件指针的初始位置：" + raf.getFilePointer());
        //移动指针位置
        raf.seek(300);
        byte[] buf = new byte[1024];
        int hasRead = 0;
        while ((hasRead = raf.read(buf)) > 0) {
            //读取数据
            System.out.println(new String(buf, 0, hasRead));
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile("out.txt", "rw");
        randomAccessFile.setLength(randomAccessFile.length());
        randomAccessFile.write("追加的内容！\r\n".getBytes());
    }

    private static void pushReader() throws IOException {
        //创建PushbackReader对象，指定推回缓冲区的长度为64
        PushbackReader pushbackReader = new PushbackReader(new FileReader("StreamTest.java"), 64);
        char[] buf = new char[32];
        //用以保存上次读取的字符串内容
        String lastContent = "";
        int hasRead = 0;
        //循环读取文件内容
        while ((hasRead = pushbackReader.read(buf)) > 0) {
            //将读取的内容转换成字符串
            String content = new String(buf, 0, hasRead);
            int targetIndex = 0;
            if ((targetIndex = (lastContent + content).indexOf("new PushbackReader")) > 0) {
                //将本次内容和上次的内容一起推回缓冲区
                pushbackReader.unread((lastContent + content).toCharArray());
                //重新定义一个长度为targetIndex的char数组
                if (targetIndex > 32) {
                    buf = new char[targetIndex];
                }
                //再次读取指定长度的内容
                pushbackReader.read(buf, 0, targetIndex);
                //打印读取的内容
                System.out.print(new String(buf, 0, targetIndex));
                System.exit(0);
            } else {
                //打印上次读取的内容
                System.out.print(lastContent);
                //将本次内容设为上次读取的内容
                lastContent = content;
            }
        }
    }

    private static void streamReader() throws IOException {
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