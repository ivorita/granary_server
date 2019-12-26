package com.ivorita.servlet;

import com.ivorita.util.RegistDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/register")
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        RegistDAO registDAO = new RegistDAO();
        try {
            boolean result = registDAO.reg_usr(username,password);
            PrintWriter pw = resp.getWriter();
            String prStr = "";
            if (result){
                prStr = "注册成功";
            } else {
                prStr = "注册失败";
            }
            //往相应里写返回内容
            pw.print(prStr);
            System.out.println(prStr);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error" + e.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
