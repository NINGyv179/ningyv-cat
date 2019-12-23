package com.ningyv.smallcat.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author LCX
 * @create 2019-12-23 13:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SqlTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void setDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
