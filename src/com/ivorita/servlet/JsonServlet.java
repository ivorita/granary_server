package com.ivorita.servlet;

import com.google.gson.Gson;
import com.ivorita.bean.GranaryInfo;
import com.ivorita.bean.InfoTotal;
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

@WebServlet("/getjson")
public class JsonServlet extends HttpServlet {

    private List<GranaryInfo> granaryInfoList = new ArrayList<>();
    private String json;
    private Connection conn = null;
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("servlet destroy");
    }

    @Override
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
        String sql = String.format("SELECT * FROM (SELECT * FROM granary_info ORDER BY id DESC LIMIT %s) gi ORDER BY id", total);

        try {
            //使用alibaba的druid连接池
            conn = JDBCUtils.getConnection();
            System.out.println("连接数据库（DAO1）");
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
        } finally {
            JDBCUtils.close(rs, pStmt, conn);//归还连接
            granaryInfoList.clear();
        }
        //获取get请求参数page的值
        /*String page = req.getParameter("page");
        List<GranaryInfo> granaryInfoList = new ArrayList<>();
        if (page == null || page.equals("0")){
            granaryInfoList.add(granaryinfo1);
            granaryInfoList.add(granaryinfo2);
        } else {
            granaryInfoList.add(granaryinfo3);
            granaryInfoList.add(granaryinfo4);
        }*/

        System.out.println(json);
        //使客户端浏览器，区分不同种类的数据，并根据不同的MIME调用浏览器内不同的程序嵌入模块来处理相应的数据
        //resp.setContentType("text/plain");
        //resp.setCharacterEncoding("gb2312");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.print(json);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取body的方法
        /*InputStream is = null;
        is = req.getInputStream();
        String body = IOUtils.toString(is,"utf-8");
        log.info("business receive something with body :"+body);*/

        req.setCharacterEncoding("UTF-8");
        float carbon_dioxide = Float.parseFloat(req.getParameter("carbon_dioxide"));
        float temperature = Float.parseFloat(req.getParameter("temperature"));
        float humidity = Float.parseFloat(req.getParameter("humidity"));
        float illumination = Float.parseFloat(req.getParameter("illumination"));
        float gas = Float.parseFloat(req.getParameter("gas"));
        //温、湿度 气体浓度 光照强度 有无光照

        System.out.println("温度：" + carbon_dioxide);
        System.out.println("湿度：" + humidity);

        RegistDAO registDAO = new RegistDAO();
        try {
            boolean result = registDAO.insertInfo(carbon_dioxide, temperature, humidity, illumination, gas);
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
}
