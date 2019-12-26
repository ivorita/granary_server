package com.ivorita.test;

import com.ivorita.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class druid {

    public static void main(String[] args) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "insert into reg_usr values(?,?)";
            //3.获取pstmt对象
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "林佳煜");
            pstmt.setString(2, "630150119");
            int count = pstmt.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);
        }

    }

}
