package cn.edu.hebuee.factory;


import cn.edu.hebuee.service.IAccountService;
import cn.edu.hebuee.service.impl.AccountServiceImpl;
import cn.edu.hebuee.utils.TransactionManger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @Classname BeanFactory
 * @Description TODO
 * @Date 2021/8/17 19:35
 * @Created by 杨军望
 */
public class BeanFactory {

    private IAccountService accountService;
    private TransactionManger transactionManger;

    public void setTransactionManger(TransactionManger transactionManger) {
        this.transactionManger = transactionManger;
    }

    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public IAccountService getAccountService(){
        Object o = Proxy.newProxyInstance(AccountServiceImpl.class.getClassLoader(),
                accountService.getClass().getInterfaces(), new InvocationHandler() {
                    Object rtValue = null;

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            transactionManger.begin();
                            rtValue = method.invoke(accountService, args);
                            transactionManger.commit();
                            return rtValue;
                        } catch (Exception e) {
                            transactionManger.rollback();
                            throw new RuntimeException("");
                        } finally {
                            transactionManger.release();
                        }
                    }
                });
        return (IAccountService) o;
    }
}
