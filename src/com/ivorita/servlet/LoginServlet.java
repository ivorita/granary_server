package com.ivorita.servlet;

import com.ivorita.util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 登录验证
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private Connection conn = null;
    private PreparedStatement pStmt = null;
    private ResultSet rs = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String usrSql = null;
        String passwordSql = null;

        HttpSession session = request.getSession();

        String sql = "SELECT * FROM admin";

        try {
            conn = JDBCUtils.getConnection();
            System.out.println("连接数据库");
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery(sql);
            while (rs.next()) {
                usrSql = rs.getString("user");
                passwordSql = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pStmt, conn);
        }

        request.setCharacterEncoding("UTF-8");
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        /*Admin admin = WebUtils.request2Bean(request, Admin.class);//获得表单传来的参数
        String user = admin.getUser();
        String password = admin.getPassword();*/

        response.setHeader("Content-type", "text/html;charset=UTF-8");

        if (usrSql != null) {
            if (passwordSql != null) {
                if (usrSql.equals(user) && passwordSql.equals(password)) {
                    session.setAttribute("admin", user);
                    //设置失效时间1小时
                    session.setMaxInactiveInterval(3600);
                    response.getWriter().write("<script language='JavaScript'>alert('登录成功');window.location.href='" + request.getContextPath() + "/home'</script>");
                    /*PrintWriter pw = new PrintWriter(response.getOutputStream());
                    pw.print("success");
                    pw.flush();*/
                } else {//登录失败
                    //response.getWriter().write("<script language='JavaScript'>alert('您的用户名或密码有误，请重新输入');window.location.href='" + request.getContextPath() + "/login.jsp'</script>");
                    request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
                }
                //如果用户名密码不对，就转发到登录界面
                /*response.getWriter().write("<script language='JavaScript'>alert('您的用户名或密码有误，请重新输入')</script>");
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);*/

            }
        }

    }
}
