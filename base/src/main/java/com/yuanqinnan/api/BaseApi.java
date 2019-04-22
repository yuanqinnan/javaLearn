package com.yuanqinnan.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class BaseApi {

    public static void main(String[] str) throws IOException {
        //scanner();
        //system();
        //runtime();
        string();
    }

    private static void string() {
        String s = new String("abcdefg");
        //获取字符串中指定位置的字符
        System.out.println(s.charAt(1));
        //比较字符串的大小
        System.out.println(s.compareTo("adasd"));
        //将该字符串与str连接在一起
        s.concat("higk");
        System.out.println(s);
        //与StringBuffer对象比较，值相等则返回true
        System.out.println(s.contentEquals(new StringBuffer("abcd")));
        //将字符数组连缀成字符串
        System.out.println(String.copyValueOf(new char[]{'a', 'b'}));
        //将字符数组的子数组连缀成字符串
        System.out.println((String.copyValueOf(new char[]{'a', 'b', 'c', 'd', 'e'}, 2, 3)));
        //将字符串与指定对象比较
        System.out.println(s.equals("abcdefg"));
        //忽略大小写字符串与指定对象比较
        System.out.println(s.equalsIgnoreCase("ABcdefg"));
        //将string对象转换成Byte数组
        byte[] bytes = s.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }
        char[] s1 = new char[]{'晚', '安', '！', '世', '界'};
        String s2 = new String("。！？");
        //将字符串从srcBegin开始，到srcEnd结束复制到字符串数组串中，dstBegin为字符串数组的初始位置
        s2.getChars(0, 2, s1, 2);
        for (int i = 0; i < s1.length; i++) {
            System.out.println(s1[i]);
        }
        //查询字符在字符串中第一次出现的位置
        System.out.println(s.indexOf('b'));
        //查询字符在字符串中从fromIndex后第一次出现在的位置
        System.out.println(s.indexOf('b', 2));
        //查询子字符串在字符串中第一次出现的位置
        System.out.println(s.indexOf("bc"));
        //查询子字符在字符串中从fromIndex后第一次出现在的位置
        System.out.println(s.indexOf("bc", 2));
        //查询字符在字符串中最后一次出现的位置
        System.out.println(s.lastIndexOf('b'));
        //查询字符在字符串中从fromIndex后最后一次出现在的位置
        System.out.println(s.lastIndexOf('b', 2));
        //查询子字符串在字符串中最后一次出现的位置
        System.out.println(s.lastIndexOf("bc"));
        //查询子字符在字符串中从fromIndex后最后一次出现在的位置
        System.out.println(s.lastIndexOf("bc", 2));
        //字符串长度
        System.out.println(s.length());
        //替换字符串
        s.replace('a', 'b');
        System.out.println(s);
        //对象是否以某字符串结尾
        System.out.println(s.startsWith("ab"));
        //String 对象是以某字串结尾
        System.out.println(s.endsWith("fg"));
        //获取从beginIndex位置开始到结束的子字符串
        String substring = s.substring(4);
        System.out.println(substring);
        //获取从beginIndex位置开始到endIndex结束的子字符串
        String substring1 = s.substring(4, 6);
        System.out.println(substring1);
        char[] chars = s.toCharArray();
        //转换成char数组
        for (int i = 0; i <chars.length ; i++) {
            System.out.println(chars[i]);
        }
        //将字符串转换成小写
        String lowerCase = s.toLowerCase();
        System.out.println(lowerCase);
        //将字符串转换成大写
        String upperCase = s.toUpperCase();
        System.out.println(upperCase);
        StringBuilder sb=new StringBuilder();
        //追加
        sb.append("java");
        System.out.println(sb);
        //插入
        sb.insert(0,"hello ");
        System.out.println(sb);
        //替换
        sb.replace(5,6,",");
        System.out.println(sb);
        //反转
        sb.reverse();
        System.out.println(sb);
        //长度与容量
        System.out.println(sb.length()+ sb.capacity());
        //StringBuffer stringBuffer=new StringBuffer();


    }

    private static void runtime() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("处理器数量：" + runtime.availableProcessors());
        System.out.println("可用最大内存数：" + runtime.maxMemory());
        System.out.println("空闲内存：" + runtime.freeMemory());
        System.out.println("总内存数：" + runtime.totalMemory());
    }

    private static void system() throws IOException {
        Map<String, String> getenv = System.getenv();
        for (String name : getenv.keySet()) {
            System.out.println(getenv.get(name));
        }
        //获取指定的环境变量
        System.out.println(System.getenv("JAVA_HOME"));
        //获取所有的系统属性
        Properties properties = System.getProperties();
        //保存至文件
        properties.store(new FileOutputStream("props.txt"), "System properties");
        System.out.println(System.getProperty("os.name"));
    }

    private static void scanner() {
        //键盘输入
        Scanner scanner = new Scanner(System.in);
        //回车作为分隔符
        scanner.useDelimiter("\n");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
        try {
            Scanner scannerfile = new Scanner(new File("123.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
