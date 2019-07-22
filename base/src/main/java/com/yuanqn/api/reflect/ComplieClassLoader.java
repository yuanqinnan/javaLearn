package com.yuanqn.api.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    private boolean compile(String javaFile) throws IOException {
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

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        String fileSub = name.replace(".", "/");
        String javaFileName = fileSub + ".java";
        String classFileName = fileSub + ".class";
        File javaFile = new File(javaFileName);
        File classFile = new File(classFileName);
        if (javaFile.exists() && (classFile.exists() || javaFile.lastModified() > classFile.lastModified())) {
            try {
                if (!compile(javaFileName) || !classFile.exists()) {
                    throw new FileNotFoundException("FileNotFoundException：" + javaFileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (classFile.exists()) {
            try {
                byte[] raw = getBytes(classFileName);
                clazz = defineClass(name, raw, 0, raw.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        return clazz;

    }
    public static void main(String[] args){
        if(args.length<1){
            System.out.println("确实目标类");
        }
        String progClass=args[0];
    }
}