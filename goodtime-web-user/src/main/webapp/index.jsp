<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: zhongcy
  Date: 2016/5/11
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <script src="js/jquery-1.12.3.min.js"></script>
    <script src="js/goodtime.js"></script>
</head>
<body>
index
<a href="${pageContext.request.contextPath}/user/test">test</a><br>
<a href="${pageContext.request.contextPath}/user/loginPage">用户注册</a>
<br>
<a href="${pageContext.request.contextPath}/task/todoList">任务列表</a>
<br>

<form id="testDate" action="${pageContext.request.contextPath}/test/testDate">
    date:<input type="text" name="beginTime" value="2016-12-12 10:10">
    <input id="submitDate" type="button">
</form>
<form id="testMapForm" action="index/testMap">
    id:<input name="id" type="text"><br>
    name:<input name="name" type="text"><br>
    <input type="submit" value="测试map">
    <input type="button" id="testMap" value="Ajax测试">
    <input type="button" id="testJsonMap" value="Json测试">
</form>
<script>
    $("#submitDate").click(function () {
        $.ajax({
                type:"post",
                url: "/test/testDate",
                data:$("#testDate").serialize(),
                success: function (data) {
                    alert(data);
                },
                error: function (e) {
                    alert(e);
                }
            }
        )
    });

    $("#testMap").click(function(){
        ajaxRequest("${pageContext.request.contextPath}/index/testMap","post",$("#testMapForm").serialize(),null)
    });
/*        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/index/testMap",
            data:$("#testMapForm").serialize(),
            success: function (data) {
                if (data != null) {
                    alert(data);
                } else {
                    alert("没有权限！");
                }
            }
        });
    });*/

    $("#testJsonMap").click(function(){
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/index/testJsonMap",
            data: JSON.stringify($("#testMapForm").serializeObject()),
            contentType: "application/json; charset=utf-8",
            success:function(data){
                alert(data);
            },
            error:function(){
                alert("error");
            }
        });
    })
</script>
</body>
</html>
