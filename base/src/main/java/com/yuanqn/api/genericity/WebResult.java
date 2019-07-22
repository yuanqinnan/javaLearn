package com.yuanqn.api.genericity;

import java.util.ArrayList;
import java.util.List;

public class WebResult<T extends Number> {
    //使用T类型形参定义实例变量
    private T data;

    public WebResult() {
    }

    //使用T类型形参构造对象
    public WebResult(T data) {
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public static void main(String[] args) {
//        WebResult<String> webResult = new WebResult<>("返回一个String对象");
//        System.out.println(webResult.getData());
        WebResult<Integer> webResult1 = new WebResult<>(10);
        System.out.println(webResult1.getData());

        WebResult<Integer> a = new WebResult<>(20);
        WebResult b = a;
        //已经擦除了泛型，只能按最高类型Object
        //Integer bData = b.getData();
        Object object = b.getData();

        List<Integer> integerList = new ArrayList<>();
        List stringList = integerList;
        //允许直接将list对象转换给
        List<String> strings = stringList;
        //直接获取数据会出现错误，因为转换不成功
        System.out.println(stringList.get(0));

    }


}