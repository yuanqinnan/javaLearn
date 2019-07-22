package com.yuanqn.api.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseApi {

    public static void main(String[] str) throws IOException, ParseException {
        //scanner();
        //system();
        //runtime();
        //string();
        //math();
        //random();
        //date();
        //pattern();
        dataFormat();


    }

    private static void dataFormat() throws ParseException {
        //将日期转换成固定格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));
        //日期字符串
        String str = "14###三月##21";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("y###MMM##d");
        //将日期字符串解析成日期，主要格式要匹配的上，不然会报错
        System.out.println(simpleDateFormat.format(simpleDateFormat1.parse(str)));

        DateTimeFormatter[] formatters = new DateTimeFormatter[]{
                //常量创建
                DateTimeFormatter.ISO_DATE_TIME,
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_DATE,
                //枚举值创建
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM),
                DateTimeFormatter.ofPattern("Gyyyy%%MMM%%dd")
        };
        LocalDateTime localDate = LocalDateTime.now();

        for (int i = 0; i < formatters.length; i++) {
            //两种方式格式化日期
            System.out.println(formatters[i].format(localDate));
            System.out.println(localDate.format(formatters[i]));
        }

    }

    private static void pattern() {
        //将字符串编译成Pattern对象
        Pattern pattern = Pattern.compile("a*b");
        //使用Pattern创建Matcher对象
        Matcher matcher = pattern.matcher("aaaaab");
        System.out.println(matcher.matches());
        //只能使用一次，效率不高
        Pattern.matches("a*b", "aaabbb");
        String s = "XXX:13892329111,XXX:18922121231,XXX:13824322341";
        Matcher matcher1 = Pattern.compile("1\\d{10}").matcher(s);
        //返回目标字符串中是否包含匹配的字串
        while (matcher1.find()) {
            //返回上一次匹配的字串
            System.out.println(matcher1.group());
        }
    }

    private static void date() {
        Date date = new Date();
        Date date1 = new Date(System.currentTimeMillis() + 100);
        //测试该日期是否在指定日期之后
        System.out.println(date.after(date1));
        //比较日期大小
        System.out.println(date.compareTo(date1));
        Calendar calendar = Calendar.getInstance();
        Date date2 = calendar.getTime();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date2);
        //给指定的字段增加或者减去时间量，如果指定量超过最大值，则进位
        calendar.add(Calendar.YEAR, 1);
        //获取指定的字段值
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DATE));
        //设置日期值
        calendar.set(2003, 10, 2, 10, 50, 10);
        //给指定的字段增加或者减去时间量，如果指定量超过最大值，不进位
        calendar.roll(Calendar.YEAR, 13);
        //Clock的用法
        Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());
        Duration duration = Duration.ofSeconds(6000);

    }

    private static void random() {
        Random random = new Random();
        //生成一个int范围内的随机数
        System.out.println(random.nextInt());
        //生成0-30范围的随机数
        System.out.println(random.nextInt(30));
        //生成0.0-1.0 的随机数
        System.out.println(random.nextDouble());
    }

    private static void math() {
        //返回小于目标数的最大整数
        System.out.println(Math.floor(-1.57));
        //返回大于目标数的最小整数
        System.out.println(Math.ceil(1.57));
        //四舍五入
        System.out.println(Math.round(2.3));
        //取绝对值
        System.out.println(Math.abs(-4.6));
        //取最大数
        System.out.println(Math.max(1, 3));
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
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }
        //将字符串转换成小写
        String lowerCase = s.toLowerCase();
        System.out.println(lowerCase);
        //将字符串转换成大写
        String upperCase = s.toUpperCase();
        System.out.println(upperCase);
        StringBuilder sb = new StringBuilder();
        //追加
        sb.append("java");
        System.out.println(sb);
        //插入
        sb.insert(0, "hello ");
        System.out.println(sb);
        //替换
        sb.replace(5, 6, ",");
        System.out.println(sb);
        //反转
        sb.reverse();
        System.out.println(sb);
        //长度与容量
        System.out.println(sb.length() + sb.capacity());
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
