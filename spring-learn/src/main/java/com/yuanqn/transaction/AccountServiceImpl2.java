package com.yuanqn.transaction;


import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Description: springLearn
 * <p>
 * Created by yuanqn on 2019/3/11 23:15
 */
public class AccountServiceImpl2 implements AccountService {

    // 业务层注入 DAO:
    private AccountDao accountDao;
    //注入事务管理
    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void transfer(final String from, final String to, final Double money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus arg0) {
                accountDao.out(from, money);
                int i = 1 / 0;
                accountDao.in(to, money);
            }
        });
    }
}
