package cn.edu.hebuee.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Classname ConnectionUtils
 * @Description 连接工具类，从数据源中获取一个连接，并且实现线程的绑定
 * @Date 2021/8/17 18:27
 * @Created by 杨军望
 */
/**
  *
  * @Description TODO
  * @param
  * @return 
  * @date 2021/8/17 18:33
  * @auther 杨军望
  */
public class ConnectionUtils {

    
    private ThreadLocal<Connection> tl = new ThreadLocal<>();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = tl.get();
        if (conn == null) {
            conn = dataSource.getConnection();
            tl.set(conn);
        }
        return conn;
    }

    /**
     * 连接与线程解绑
     */
    public void removeConnection(){
        tl.remove();
    }
}
