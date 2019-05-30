package com.yuanqinnan.api.io;

import java.io.File;
import java.io.IOException;

public class FileTest {

    public static void main(String[] str) throws IOException {

        //在当前路径来创建一个File对象
        File file = new File("1.txt");
        //判断文件或目录是否存在
        System.out.println(file.exists());
        //判断是否是文件
        System.out.println(file.isFile());
        //判断是否是目录
        System.out.println(file.isDirectory());
        //是否为绝对路径
        System.out.println(file.isAbsolute());
        //文件或目录是否可读
        System.out.println(file.canRead());
        //文件或目录是否可写
        System.out.println(file.canWrite());
        //文件或目录最后修改时间
        System.out.println(file.lastModified());
        //创建文件
        System.out.println(file.createNewFile());
        //当文件或者路径不存在是创建改路径，创建成功返回true
        file.renameTo(new File("2.txt"));
        //返回文件内容长度
        System.out.println(file.length());
        //获取文件或目录名
        System.out.println(file.getName());
        //获取文件或目录相对路径
        System.out.println(file.getPath());
        //获取文件或目录绝对路径
        System.out.println(file.getAbsolutePath());
        //获取上一级路径
        System.out.println(file.getAbsoluteFile().getParent());
        //退出时删除目录或文件
        file.deleteOnExit();
        //以当前路径来创建一个File对象
        File file1 = new File(".");
        //创建一个临时文件，虚拟机退出时会自动删除
        File tempFile = File.createTempFile("aaa", ".txt", file1);
        //返回当前目录的子目录或文件的名称
        String[] list = file1.list();
        for (String fileName : list) {
            System.out.println(fileName);
        }
        //返回当前目录的子目录或文件，返回的是File数组
        File[] files = file1.listFiles();
        //返回系统的所有根路径
        File[] listRoots = File.listRoots();
        for (File root : listRoots) {
            System.out.println(root);
        }

        File file2 = new File("temp");
        //创建对象对应的目录
        System.out.println(file2.mkdir());
        //把文件重命名为指定的文件路径
        file2.renameTo(new File("temp2"));
        //删除文件或者文件夹
        file2.delete();
    }

}