<%--
  Created by IntelliJ IDEA.
  User: ivorita
  Date: 2019/3/1
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>  <%--导入java.sql包--%>
<html>

  <head>
    <title>从MySQL数据库中读出表</title>
  </head>

  <body>

  <%

      try {
          Class.forName("com.mysql.jdbc.Driver");  ////驱动程序名
          String url = "jdbc:mysql://localhost:3306/class"; //数据库名
          String username = "root";  //数据库用户名
          String password = "trio123";  //数据库用户密码
          Connection conn = DriverManager.getConnection(url, username, password);  //连接状态

          if (conn != null) {
              out.print("<br />");

  %>

  <table border="1">

      <tr>
          <td width="100" name="title">姓名</td>
      </tr>

      <%
          Statement stmt = null;
          ResultSet rs = null;
          String sql = "SELECT username FROM reg_usr;";  //查询语句
          stmt = conn.createStatement();
          rs = stmt.executeQuery(sql);
          out.print("查询结果：");
          out.print("<br/>");
          while (rs.next()) {%>

      <tr>
          <td width="100" ><%=rs.getString("username") %></td>
      </tr>

      <%
                  }
              }else{
                  out.print("连接失败！");
              }
          }catch (Exception e) {
              //e.printStackTrace();
              out.print("数据库连接异常！");
          }

      %>

  </table>
  </body>
</html>
