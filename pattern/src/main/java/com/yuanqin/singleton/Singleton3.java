package com.yuanqin.singleton;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yuanqinnan
 * \* Date: 2018/12/24
 * \* Time: 11:11
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Singleton3 {

    private static class SingletonHolder {
        private static final Singleton3 INSTANCE = new Singleton3();
    }

    private Singleton3() {
    }

    public static final Singleton3 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
