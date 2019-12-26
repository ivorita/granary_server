package com.ivorita.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Druid连接池的工具类
 */
public class JDBCUtils {

    //1.定义成员变量 DataSource
    private static DataSource ds;

    static {
        //1.加载配置文件
        Properties pro = new Properties();
        try {
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //2、获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn) {
        close(null, stmt, conn);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {

        if (rs != null) {
            try {
                rs.close();//归还连接
                System.out.println("归还rs连接");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
                System.out.println("归还stmt连接");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
                System.out.println("归还conn连接");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        try {
//            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 获取连接池
     */
    public static DataSource getDataSource(){
        return ds;
    }
}
