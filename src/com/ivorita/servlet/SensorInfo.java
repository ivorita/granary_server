package com.ivorita.servlet;

import com.google.gson.Gson;
import com.ivorita.bean.GranaryInfo;
import com.ivorita.bean.InfoTotal;
import com.ivorita.bean.sensor_info;
import com.ivorita.bean.sensor_infoTotal;
import com.ivorita.util.JDBCUtils;
import com.ivorita.util.RegistDAO;

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

@WebServlet("/SensorInfo")
public class SensorInfo extends HttpServlet {

    private List<sensor_info> sensorInfoList = new ArrayList<>();
    private String json;
    private Connection conn = null;
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        float temperature = Float.parseFloat(req.getParameter("temperature"));
        float humidity = Float.parseFloat(req.getParameter("humidity"));
        float illumination = Float.parseFloat(req.getParameter("illumination"));
        int illuex = Integer.parseInt(req.getParameter("illuex"));
        float gas = Float.parseFloat(req.getParameter("gas"));
        //温、湿度 气体浓度 光照强度 有无光照

        RegistDAO registDAO = new RegistDAO();
        try {
            boolean result = registDAO.insert2si(temperature, humidity, gas, illumination, illuex);
            PrintWriter pw = resp.getWriter();
            String prStr;
            if (result) {
                prStr = "insert success";
            } else {
                prStr = "insert fail";
            }
            //往相应里写返回内容
            pw.print(prStr);
            System.out.println(prStr);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-type", "application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        String total = req.getParameter("total");

        if (total == null) {
            total = "15";
        }

        //查询最新的n条数据，并顺序排序
        String sql = String.format("SELECT * FROM (SELECT * FROM sensor_info ORDER BY id DESC LIMIT %s) gi ORDER BY id", total);

        try {
            //使用alibaba的druid连接池
            conn = JDBCUtils.getConnection();
            System.out.println("连接数据库（DAO1）");
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {

                    int id = rs.getInt("id");
                    float temperature = rs.getFloat("temperature");
                    float humidity = rs.getFloat("humidity");
                    float illumination = rs.getFloat("illumination");
                    float gas = rs.getFloat("gas");
                    int illuex = rs.getInt("illuex");

                    sensor_info granaryInfo = new sensor_info(id, temperature, humidity, gas, illumination,illuex);

                    sensorInfoList.add(granaryInfo);

                    sensor_infoTotal sensor_infoTotal = new sensor_infoTotal(sensorInfoList.size(), sensorInfoList);
                    Gson gson = new Gson();
                    json = gson.toJson(sensor_infoTotal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pStmt, conn);//归还连接
            sensorInfoList.clear();
        }

        System.out.println(json);
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.print(json);
        pw.flush();
    }
}
