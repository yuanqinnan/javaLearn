package com.yuanqinnan.api.nio;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class NioTest {
    public static void main(String[] str) throws IOException {
        //buffer();
        File file = new File("StreamTest.java");
        //输入流创建FileChannel
        FileChannel inChannel = new FileInputStream(file).getChannel();
        //以文件输出流创建FileChannel，控制输出
        FileChannel outChannel = new FileOutputStream("a.txt").getChannel();
        //将FileChannel映射成ByteBuffer，
        MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        Charset charset = Charset.forName("GBK");
        //输出数据
        outChannel.write(buffer);
        buffer.clear();
        CharsetDecoder charsetDecoder = charset.newDecoder();
        //转换成CharBuffer进行输出
        CharBuffer charBuffer = charsetDecoder.decode(buffer);
        System.out.println(charBuffer);

    }

    private static void buffer() {
        //创建buffer
        CharBuffer buffer = CharBuffer.allocate(10);
        System.out.println("capacity: " + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());
        //加入数据
        buffer.put('a');
        buffer.put('b');
        buffer.put('c');
        System.out.println("加入元素后，position:" + buffer.position());
        buffer.flip();
        System.out.println("执行flip后，limit：" + buffer.limit());
        System.out.println("position:" + buffer.position());
        System.out.println("取出一个数据，" + buffer.get());
        System.out.println("取出数据后，position：" + buffer.position());
        buffer.clear();
        System.out.println("执行clear后，limit：" + buffer.limit());
        System.out.println(",position:" + buffer.position());
        System.out.println("执行clear后缓冲区未被清空：" + buffer.get(2));
        System.out.println("绝对读取后，position不会改变：" + buffer.position());
    }
}