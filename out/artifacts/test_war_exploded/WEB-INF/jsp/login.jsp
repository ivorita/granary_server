<%--
  Created by IntelliJ IDEA.
  User: ivorita
  Date: 2019/5/2
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>请登录</title>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <!-- 引入css样式文件 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Resources/css/login.css">

</head>
<body>

    <div class="container">

        <h3>后台管理系统</h3>

        <!-- 登录表单 -->
        <form action="${pageContext.request.contextPath }/login" method="post">

            <div class="form-group">
                <input type="text" class="form-control" id="user" name="user" placeholder="账号"/>
            </div>

            <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="密码"/>
            </div>

            <div class="form-check">
                <label>
                    <input class="form-check-input" type="checkbox" name="rememberMe">记住我
                </label>
            </div>

            <div class="form-button">
                <button type="submit" class="btn btn-primary">登录</button>
                <button type="reset" class="btn btn-secondary">重置</button>
            </div>

        </form>

    </div>

</body>
</html>
