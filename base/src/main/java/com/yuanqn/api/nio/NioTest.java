package com.yuanqn.api.nio;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class NioTest {
    public static void main(String[] str) throws IOException {
        //channel();
        //charset();
        //files();
        BasicFileAttributeView baseView = Files.getFileAttributeView(Paths.get("poem.txt"), BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = baseView.readAttributes();
        System.out.println("创建时间：" + basicFileAttributes.creationTime().toMillis());
        System.out.println("最后更新时间：" + basicFileAttributes.lastModifiedTime().toMillis());
        return;

    }

    private static void files() throws IOException {
        Path path = Paths.get(".");
        System.out.println("path包含的文件数量：" + path.getNameCount());
        System.out.println("path的根路径：" + path.getRoot());
        Path path1 = path.toAbsolutePath();
        System.out.println("path的绝对路径：" + path1);
        //多个String构建路径
        Path path2 = Paths.get("G:", "test", "codes");
        System.out.println("path2的路径：" + path2);

        System.out.println("StreamTest.java是否为隐藏文件:" + Files.isHidden(Paths.get("StreamTest.java")));
        //一次性读取所有行
        List<String> allLines = Files.readAllLines(Paths.get("StreamTest.java"), Charset.forName("gbk"));
        System.out.println(allLines);
        //读取大小
        System.out.println("StreamTest.java文件大小：" + Files.size(Paths.get("StreamTest.java")));
        List<String> poem = new ArrayList<>();
        poem.add("问君能有几多愁");
        poem.add("恰似一江春水向东流");
        //一次性写入数据
        Files.write(Paths.get("poem.txt"), poem, Charset.forName("gbk"));
    }

    private static void charset() throws CharacterCodingException {
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        for (String name : stringCharsetSortedMap.keySet()) {
            System.out.println(name);
        }
        //创建简体中文对应的Charset
        Charset cn = Charset.forName("GBK");
        //创建对应的编码器及解码器
        CharsetEncoder cnEncoder = cn.newEncoder();
        CharsetDecoder cnDecoder = cn.newDecoder();
        CharBuffer buff = CharBuffer.allocate(8);
        buff.put('李');
        buff.put('白');
        buff.flip();
        //将buff的字符转成字节序列
        ByteBuffer bbuff = cnEncoder.encode(buff);
        for (int i = 0; i < bbuff.capacity(); i++) {
            System.out.print(bbuff.get(i) + " ");
        }
        //将bbuff的数据解码成字符
        System.out.println("\n" + cnDecoder.decode(bbuff));
    }

    private static void channel() throws IOException {
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