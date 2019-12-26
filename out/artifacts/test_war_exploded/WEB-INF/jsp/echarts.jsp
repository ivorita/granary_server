<%--
  Created by IntelliJ IDEA.
  User: ivorita
  Date: 2019/5/4
  Time: 0:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>折线图</title>
    <script src="https://cdn.bootcss.com/echarts/4.2.1-rc1/echarts.min.js"></script>
</head>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 800px;height:500px;padding: 20px"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        option3 = {
            title: {
                text: '数据折线图'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['温度','湿度','二氧化碳浓度']
            },
            grid: {
                left: '4%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ['周一','周二','周三','周四','周五','周六','周日']
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name:'温度',
                    type:'line',
                    stack: '总量',
                    data:[23, 24, 21, 20, 19, 23, 25]
                },
                {
                    name:'湿度',
                    type:'line',
                    stack: '总量',
                    data:[22, 18, 19, 23, 29, 33, 31]
                },
                {
                    name:'二氧化碳浓度',
                    type:'line',
                    stack: '总量',
                    data:[150, 232, 201, 154, 190, 330, 410]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option3);
    </script>
</body>
</html>
