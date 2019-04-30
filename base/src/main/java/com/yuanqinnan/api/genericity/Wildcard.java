package com.yuanqinnan.api.genericity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Wildcard {

    public static void main(String[] str) {
//        ArrayList<String> arrayList = new ArrayList();
//        test(arrayList);
//        List<?> strings = arrayList;
//        ArrayList<Animal> animals = new ArrayList<>();
//        animals.add(new Cat());
//        test1(animals);
//        Object[] objects = new Object[10];
//        List<Object> list = new ArrayList<>();
//        arrayToList(objects, list);
//
//        Integer[] integers = new Integer[10];
//        List<Integer> integerList = new ArrayList();
//        arrayToList(integers, integerList);
//
//        String[] strings = new String[10];
//        List<String> stringList = new ArrayList<>();
//        arrayToList(strings, stringList);
        //编译错误，类型不正确
        //arrayToList(strings, integerList);
        List<Number> ln = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        //编译出错，类型不确定
        Integer last = copy(ln, li);

    }

    public static void test(List<?> test) {
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i));
        }
    }

    public static void test1(List<? extends Animal> animals) {
        for (int i = 0; i < animals.size(); i++) {
            animals.get(i).say();
        }
    }

    public static <T> void arrayToList(T[] a, List<T> list) {
        for (T o : a) {
            list.add(o);
        }
    }

    //将src中的集合复制到dest，并返回最后一个值
    public static <T> T copy(Collection<? super T> dest, Collection<? extends T> src) {
        T last = null;
        for (T ele : src) {
            last = ele;
            dest.add(ele);
        }
        return last;
    }


}
