package cn.edu.hebuee.utils;

import java.sql.SQLException;

/**
 * @Classname TransactionManger
 * @Description 事务管理
 * @Date 2021/8/17 18:36
 * @Created by 杨军望
 */
public class TransactionManger {
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void begin()  throws SQLException{
        connectionUtils.getConnection().setAutoCommit(false);
    }
    public void commit()  throws SQLException{
        connectionUtils.getConnection().commit();
    }
    public void rollback()  throws SQLException{
        connectionUtils.getConnection().rollback();
    }
    public void release()  throws SQLException{
        connectionUtils.getConnection().close();
        connectionUtils.removeConnection();
    }

}
