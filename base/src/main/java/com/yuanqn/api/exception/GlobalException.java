package com.yuanqn.api.exception;

public class GlobalException extends RuntimeException {

    //无参构造器
    public GlobalException() {
    }

    //带有错误描述信息的构造器
    public GlobalException(String msg) {
        super(msg);
    }
}