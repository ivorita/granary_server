<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>后台管理系统</title>

    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <%--<link rel="stylesheet"
          href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/Resources/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <%--<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath }/Resources/js/jquery.min.js"></script>
    <!-- 引入本页的js文件 -->
    <script src="${pageContext.request.contextPath}/Resources/js/ajax_main.js"></script>

    <!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/Resources/js/popper.min.js"></script>
    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <%--<script
            src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath }/Resources/js/echarts.min.js"></script>
    <!-- 引入Vue -->
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>

    <!-- 导入前端数据校验插件 -->
    <script
            src="https://cdn.staticfile.org/nice-validator/1.1.4/jquery.validator.min.js?local=zh-CN"></script>

    <!-- 引入css文件 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/Resources/css/ajax_main.css">

</head>

<body>

<div class="top">
    <h3>欢迎使用智能粮仓环境监测系统</h3>
    <input type="hidden" value="${pageContext.request.contextPath}"
           id="path">
    <input type="hidden" value=${sessionScope.permission}
            id="permission">
</div>

<div class="bottom">
    <div class="index ">
        <ul class="nav nav-pills flex-column nav-justified">
            <li class="nav-item"><a class="nav-link active"
                                    href="${pageContext.request.contextPath}/RealTimeData" value="RealTimeData">实时数据</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/HistoricalData" value="HistoricalData">历史数据</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/LineChart" value="LineChart">折线图</a></li>
        </ul>
    </div>
    <div class="contain" id="contain"></div>
</div>

</body>
</html>
