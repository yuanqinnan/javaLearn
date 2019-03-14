package com.yuanqinnan.transaction;


/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/11 23:15
 */
public class AccountServiceImpl implements AccountService {
    // 业务层注入 DAO:
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void transfer(String from, String to, Double money) {
        accountDao.out(from,money);
        int i=1/0;
        accountDao.in(to,money);
    }
}
