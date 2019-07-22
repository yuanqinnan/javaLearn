package com.yuanqn.transaction;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/11 23:09
 */
public interface AccountService {

    void transfer(String from, String to, Double money);
}
