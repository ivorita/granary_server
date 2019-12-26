package com.ivorita.servlet;

import com.google.gson.Gson;
import com.ivorita.bean.GranaryInfo;
import com.ivorita.bean.InfoTotal;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询历史记录数据
 */
@WebServlet("/QueryHistory")
public class QueryHistory extends HttpServlet {

    private List<GranaryInfo> granaryInfoList = new ArrayList<>();
    private String json;
    private Connection conn = null;
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String start = req.getParameter("start");
        String end = req.getParameter("end");

        String sql = "select * from granary_info where time between" + " '" + start + "' " + "and DATE_ADD(" + " '" + end + "' " + ",INTERVAL 1 DAY)";
        try {
            //使用alibaba的druid连接池
            conn = JDBCUtils.getConnection();
            System.out.println("Query History : 连接数据库");
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");

                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String timeStr = df.format(rs.getTimestamp("time"));

                    float temperature = rs.getFloat("temperature");

                    float humidity = rs.getFloat("humidity");
                    float carbon_dioxide = rs.getFloat("carbon_dioxide");
                    float illumination = rs.getFloat("illumination");
                    float gas = rs.getFloat("gas");

                    System.out.println("id: " + id + "  time: " + timeStr + "temperature：" + temperature + "  humidity: " + humidity + "  carbon_dioxide：" + carbon_dioxide);

                    GranaryInfo granaryInfo = new GranaryInfo(id, timeStr, carbon_dioxide, temperature, humidity, illumination, gas);

                    granaryInfoList.add(granaryInfo);

                    InfoTotal nt = new InfoTotal(granaryInfoList.size(), granaryInfoList);
                    Gson gson = new Gson();
                    json = gson.toJson(nt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());
        } finally {
            JDBCUtils.close(rs, pStmt, conn);//归还连接
            granaryInfoList.clear();
        }

        System.out.println(json);
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.print(json);
        pw.flush();

    }
}
