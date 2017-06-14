package test.jdbc;

import org.junit.Test;

import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

/**
 * Created by Aaron on 2017/6/14 0014.
 */
public class JDBCTest {

    public static void main(String[] args) throws Exception {
        JDBCTest jdbcTest = new JDBCTest();
        jdbcTest.getConnection();
    }

    @Test
    public static void TestDriver() throws Exception {
        //1.创建一个Driver 实现类对象
        Driver driver = new com.mysql.jdbc.Driver();

        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        String url = "jdbc:mysql://localhost:3306/test";

        Connection connection = driver.connect(url, info);
        System.out.println(connection);

    }

    @Test
    public Connection getConnection() throws Exception {
        String username = null;
        String password = null;
        String url = null;
        String driver = null;

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        username = properties.getProperty("username");
        password = properties.getProperty("password");
        url = properties.getProperty("url");
        driver = properties.getProperty("driver");


        return null;
    }
}
