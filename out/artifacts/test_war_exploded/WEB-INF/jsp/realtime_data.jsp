<%--
  Created by IntelliJ IDEA.
  User: ivorita
  Date: 2019/5/4
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>实时数据</title>
    <!-- jQuery文件 -->
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <!-- ECharts -->
    <script src="https://cdn.bootcss.com/echarts/4.2.1-rc1/echarts.min.js"></script>
    <style>
        #wrap {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
<div id="wrap">
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="temperature" style="width: 280px;height:280px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化ECharts实例
        var myChart1 = echarts.init(document.getElementById('temperature'));

        var colorTemplate1 = [[0.2, "rgba(30,144,255)"], [0.8, "rgba(0,255,0,0.8)"], [1, "rgba(255,165,0)"]];

        option1 = {
            tooltip: {
                formatter: "{a} <br/>{b} : {c}"
            },
            series: [
                {
                    name: '温度值',
                    type: 'gauge',
                    min: -30,
                    max: 50,
                    detail: {
                        formatter: '{value}℃',
                        fontSize: 14
                    },
                    title: {                // 仪表盘标题。
                        color: "#333",          // 文字的颜色,默认 #333。
                        fontSize: 14           // 文字的字体大小,默认 15。
                    },
                    axisLine: {             // 仪表盘轴线(轮廓线)相关配置。
                        show: true,             // 是否显示仪表盘轴线(轮廓线),默认 true。
                        lineStyle: {            // 仪表盘轴线样式。
                            color: colorTemplate1,  //仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
                            opacity: 1,                 //图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                            width: 20,                  //轴线宽度,默认 30。
                            shadowBlur: 20,             //(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                            shadowColor: "#fff"        //阴影颜色。支持的格式同color。
                        }
                    },
                    pointer: {              // 仪表盘指针。
                        show: true,             // 是否显示指针,默认 true。
                        length: "70%",          // 指针长度，可以是绝对数值，也可以是相对于半径的百分比,默认 80%。
                        width: 5               // 指针宽度,默认 8。
                    },

                    data: [{value: 0, name: '温度'}]
                }
            ]
        };
        myChart1.setOption(option1);

        $(document).ready(function () {
            setInterval("start1()", 3000);
        });

        function start1() {
            $.get("http://localhost:8080/trio/getjson", "total=1", function (data, status) {
                //$("#mainContent").html(data.rows[0].carbon_dioxide);//要刷新的div
                option1.series[0].data[0].value = data.rows[0].temperature;
                myChart1.setOption(option1, true);
            })
        }

    </script>

    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="humidity" style="width: 280px;height:280px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化ECharts实例
        var myChart2 = echarts.init(document.getElementById('humidity'));

        var colorTemplate1 = [[0.2, "rgba(30,144,255)"], [0.8, "rgba(0,255,0,0.8)"], [1, "rgba(255,165,0)"]];

        option2 = {
            tooltip: {
                formatter: "{a} <br/>{b} : {c}"
            },
            series: [
                {
                    name: '湿度值',
                    type: 'gauge',
                    min: 0,
                    max: 100,
                    detail: {
                        formatter: '{value}%',
                        fontSize: 14
                    },
                    title: {                // 仪表盘标题。
                        color: "#333",          // 文字的颜色,默认 #333。
                        fontSize: 14           // 文字的字体大小,默认 15。
                    },
                    axisLine: {             // 仪表盘轴线(轮廓线)相关配置。
                        show: true,             // 是否显示仪表盘轴线(轮廓线),默认 true。
                        lineStyle: {            // 仪表盘轴线样式。
                            color: colorTemplate1,  //仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
                            opacity: 1,                 //图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                            width: 20,                  //轴线宽度,默认 30。
                            shadowBlur: 20,             //(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                            shadowColor: "#fff"        //阴影颜色。支持的格式同color。
                        }
                    },
                    pointer: {              // 仪表盘指针。
                        show: true,             // 是否显示指针,默认 true。
                        length: "70%",          // 指针长度，可以是绝对数值，也可以是相对于半径的百分比,默认 80%。
                        width: 5               // 指针宽度,默认 8。
                    },

                    data: [{value: 0, name: '湿度'}]
                }
            ]
        };
        myChart2.setOption(option2);

        $(document).ready(function () {
            setInterval("start2()", 3000);
        });

        function start2() {
            $.get("http://localhost:8080/trio/getjson", "total=1", function (data, status) {
                //$("#mainContent").html(data.rows[0].carbon_dioxide);//要刷新的div
                option2.series[0].data[0].value = data.rows[0].humidity;
                myChart2.setOption(option2, true);
            })
        }

    </script>

    <div id="carbon" style="width: 280px;height:280px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化ECharts实例
        var myChart3 = echarts.init(document.getElementById('carbon'));

        var colorTemplate1 = [[0.2, "rgba(30,144,255)"], [0.8, "rgba(0,255,0,0.8)"], [1, "rgba(255,165,0)"]];

        option3 = {
            tooltip: {
                formatter: "{a} <br/>{b} : {c}"
            },
            series: [
                {
                    name: 'CO₂浓度值',
                    type: 'gauge',
                    min: 0,
                    max: 800,
                    detail: {
                        formatter: '{value}ppm',
                        fontSize: 14
                    },
                    title: {                // 仪表盘标题。
                        color: "#333",          // 文字的颜色,默认 #333。
                        fontSize: 14           // 文字的字体大小,默认 15。
                    },
                    axisLine: {             // 仪表盘轴线(轮廓线)相关配置。
                        show: true,             // 是否显示仪表盘轴线(轮廓线),默认 true。
                        lineStyle: {            // 仪表盘轴线样式。
                            color: colorTemplate1,  //仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
                            opacity: 1,                 //图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                            width: 20,                  //轴线宽度,默认 30。
                            shadowBlur: 20,             //(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                            shadowColor: "#fff"        //阴影颜色。支持的格式同color。
                        }
                    },
                    pointer: {              // 仪表盘指针。
                        show: true,             // 是否显示指针,默认 true。
                        length: "70%",          // 指针长度，可以是绝对数值，也可以是相对于半径的百分比,默认 80%。
                        width: 5               // 指针宽度,默认 8。
                    },
                    data: [{value: 0, name: 'CO₂浓度'}]
                }
            ]
        };
        myChart3.setOption(option3);

        $(document).ready(function () {
            setInterval("start3()", 3000);
        });

        function start3() {
            $.get("http://localhost:8080/trio/getjson", "total=1", function (data, status) {
                option3.series[0].data[0].value = data.rows[0].carbon_dioxide;
                myChart3.setOption(option3, true);
            })
        }

    </script>
</div>


</body>
</html>
