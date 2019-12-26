<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.ivorita.util.JDBCUtils" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--不加会乱码--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <title>传感器数据</title>

</head>
<body>

<div class="container">
    <h1>传感器数据</h1>
    <table class="table table-bordered table-hover">

        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">日期</th>
            <th scope="col">二氧化碳</th>
            <th scope="col">湿度</th>
        </tr>
        </thead>

        <%
            Connection conn = null;
            PreparedStatement pStmt = null;
            ResultSet rs = null;

            //查询最新的n条数据，并顺序排序
            String sql = String.format("SELECT * FROM (SELECT * FROM granary_info ORDER BY id DESC LIMIT %s) gi ORDER BY id", 10);

            try {
                conn = JDBCUtils.getConnection();
                System.out.println("连接数据库");
                pStmt = conn.prepareStatement(sql);
                rs = pStmt.executeQuery(sql);
                while (rs.next()) {%>

        <tbody>

        <tr>
            <th scope="row"><%=rs.getInt("id")%></th>

            <%DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>

            <td><%=df.format(rs.getTimestamp("time"))%></td>
            <td><%=rs.getFloat("humidity")%></td>
            <td><%=rs.getFloat("carbon_dioxide")%></td>
        </tr>

        </tbody>

        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.close(rs, pStmt, conn);//归还连接
            }
        %>

    </table>
</div>

</body>
</html>