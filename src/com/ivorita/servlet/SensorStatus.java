package com.ivorita.servlet;

import com.ivorita.util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/SensorStatus")
public class SensorStatus extends HttpServlet {

    private String sql;
    private Connection conn = null;
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;
    private int ledStatus;
    private PrintWriter pw;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-type", "application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        resp.setHeader("Access-Control-Allow-Credentials", "true");*/

        //查询最新的1条数据
        sql = String.format("SELECT * FROM (SELECT * FROM sensor_status ORDER BY id DESC LIMIT %s) gi ORDER BY id", 1);

        try {
            //使用alibaba的druid连接池
            conn = JDBCUtils.getConnection();
            System.out.println("连接数据库（DAO1）");
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    ledStatus = rs.getInt("led_status");
                }
            }
            pw = resp.getWriter();
            pw.print(ledStatus);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("错误" + e.toString());
        } finally {
            pw.close();
            JDBCUtils.close(rs, pStmt, conn);//归还连接
        }

    }
}
