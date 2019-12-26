package com.ivorita.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistDAO {

    private Connection conn = null;
    private PreparedStatement stat = null;

    //用户注册
    public boolean reg_usr(String username, String password) throws SQLException {

        int rowCount = 0;
        try {
            conn = JDBCUtils.getConnection();
            System.out.println("连接数据库（DAO）");
            String sql = "insert into reg_usr(username,password) value(?,?)";
            stat = conn.prepareStatement(sql);
            stat.setString(1, username);
            stat.setString(2, password);
            rowCount = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(stat, conn);
        }
        return rowCount > 0;
    }

    //插入数据
    public boolean insertInfo(float carbon_dioxide, float temperature, float humidity, float illumination, float gas) throws SQLException {

        int rowCount = 0;
        try {
            conn = JDBCUtils.getConnection();
            System.out.println("连接数据库（DAO）");
            String sql = "insert into granary_info(carbon_dioxide,temperature,humidity,illumination,gas) value(?,?,?,?,?)";
            stat = conn.prepareStatement(sql);
            stat.setString(1, String.valueOf(carbon_dioxide));
            stat.setString(2, String.valueOf(temperature));
            stat.setString(3, String.valueOf(humidity));
            stat.setString(4, String.valueOf(illumination));
            stat.setString(5, String.valueOf(gas));
            rowCount = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(stat, conn);
        }
        return rowCount > 0;
    }

    public boolean insert2si(float temperature, float humidity, float illumination, float gas,int illuex) throws SQLException {

        int rowCount = 0;
        try {
            conn = JDBCUtils.getConnection();
            System.out.println("连接数据库（DAO）");
            String sql = "insert into sensor_info(temperature,humidity,gas,illumination,illuex) value(?,?,?,?,?)";
            stat = conn.prepareStatement(sql);
            stat.setString(1, String.valueOf(temperature));
            stat.setString(2, String.valueOf(humidity));
            stat.setString(3, String.valueOf(gas));
            stat.setString(4, String.valueOf(illumination));
            stat.setString(5, String.valueOf(illuex));
            rowCount = stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(stat, conn);
        }
        return rowCount > 0;
    }


}
