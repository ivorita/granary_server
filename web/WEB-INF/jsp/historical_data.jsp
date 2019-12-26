<%--不加会乱码--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <%--<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/Resources/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <%--<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath }/Resources/js/jquery.min.js"></script>

    <!-- 引入本页的js文件 -->
    <script src="${pageContext.request.contextPath }/Resources/js/ajax_main.js"></script>

    <%--<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>--%>

    <!-- 引入css文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/sensor_data.css">

    <title>传感器数据</title>

</head>
<body>

<div class="contain">

    <div class="title">
        <h3>传感器数据</h3>
    </div>

    <table class="table table-striped table-hover" id="table-content">

        <thead>
        <tr>
            <th>#</th>
            <th>日期</th>
            <th>二氧化碳</th>
            <th>温度</th>
            <th>湿度</th>
            <th>光照强度</th>
            <th>可燃气体浓度</th>
        </tr>
        </thead>

        <tbody id="tbody">
        </tbody>
    </table>
</div>

</body>
</html>