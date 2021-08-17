package cn.edu.hebuee.test;

import cn.edu.hebuee.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

/**
 * 使用Junit单元测试：测试我们的配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

//    有多个方法
    @Autowired
    @Qualifier("proxyAccountService")
    private IAccountService as;

    @Test
    public void testTransfer() throws SQLException {
        as.transfer("aaa","bbb",500f);
    }
}
