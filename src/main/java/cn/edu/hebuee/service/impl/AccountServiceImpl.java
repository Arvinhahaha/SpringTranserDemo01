package cn.edu.hebuee.service.impl;

import cn.edu.hebuee.dao.IAccountDao;
import cn.edu.hebuee.domain.Account;
import cn.edu.hebuee.service.IAccountService;
import cn.edu.hebuee.utils.TransactionManger;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;



    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() throws SQLException {


            return accountDao.findAllAccount();

    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public List<Account> findAccountByName(String name) {
        return accountDao.findAccountByName(name);
    }


    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        accountDao.deleteAccount(acccountId);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) throws SQLException {
        List<Account> p1 = accountDao.findAccountByName(sourceName);
        List<Account> p2 = accountDao.findAccountByName(targetName);
        if (p1 != null && p2 != null && p1.size() == 1 && p2.size() == 1) {
            p1.get(0).setMoney(p1.get(0).getMoney() - money);
            p2.get(0).setMoney(p2.get(0).getMoney() + money);
            accountDao.updateAccount(p1.get(0));
            accountDao.updateAccount(p2.get(0));
    }}
}

