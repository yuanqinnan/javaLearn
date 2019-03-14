package com.yuanqinnan.transaction;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/11 23:04
 */
//转账实现
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao   {
    public void out(String outer, Double money) {
        this.getJdbcTemplate().update("update account set money = money - ? where username = ?",money,outer);
    }

    public void in(String in, Double money) {
        this.getJdbcTemplate().update("update account set money = money + ? where username = ?",money,in);
    }
}
