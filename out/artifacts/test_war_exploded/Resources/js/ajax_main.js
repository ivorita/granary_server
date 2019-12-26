/*
 * 点击侧边的菜单事件
 * 让main根据地址刷新相对应的内容
 */
$(function () {

    $(".nav li a").click(function () {
        var type = $(this).attr("value");
        var address = $(this).attr("href");
        $("a").removeClass("active")
        $(this).addClass("active");
        $('#contain').html("");
        $('#contain').load(address);

        switch (type) {
            case 'HistoricalData':
                requestContent("getjson");
                break;
            case 'LineChart':
                requestChartsContent("getjson");
                break;
        }
        return false;
    });

});

function requestContent(url) {
     /*ajax实现方式get
     语法：$.get(url, [data], [callback], [type])
        * 参数：
            * url：请求路径
            * data：请求参数
            * callback：回调函数
            * type：响应结果的类型*/
    $.get(url, function (data, status) {
        var size = data.total;
        var res = "";
        for (var i = 0; i < size; i++) {
            res += "<tr>" + "<td>" + data.rows[i].id + "</td>" +
                "<td>" + data.rows[i].datetime + "</td>" +
                "<td>" + data.rows[i].carbon_dioxide + "</td>" +
                "<td>" + data.rows[i].temperature + "</td>" +
                "<td>" + data.rows[i].humidity + "</td>" + "</tr>";
        }
        $("#tbody").html("");
        $("#tbody").append(res);
    });
}

function requestChartsContent(url) {
    $.get(url, function (data, status) {

    });

}