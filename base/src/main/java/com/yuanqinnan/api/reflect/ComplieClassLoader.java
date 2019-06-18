package com.yuanqinnan.api.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ComplieClassLoader extends ClassLoader {

    private byte[] getBytes(String fileName) throws IOException {
        File file = new File(fileName);
        long length = file.length();
        byte[] raw = new byte[(int) length];
        try (
                FileInputStream fileInputStream = new FileInputStream(file)) {
            int r = fileInputStream.read(raw);
            if (r != length) {
                throw new IOException("无法读取全部文件");
            }
            return raw;

        }
    }

    private boolean complie(String javaFile) throws IOException {
        System.out.println("正在编译文件" + javaFile + "...");
        Process p = Runtime.getRuntime().exec("javac " + javaFile);
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int ret = p.exitValue();
        return ret == 0;
    }

    protected Class<?> findClass(String name) {
        Class clazz = null;
        String fileSub = name.replace(".", "/");
        return clazz;

    }

}