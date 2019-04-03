package com.yuanqinnan.transaction;


/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/11 22:58
 */
public interface AccountDao {
    //转出
    void out(String outer, Double money);

    //转入
    void in(String in, Double money);
}
