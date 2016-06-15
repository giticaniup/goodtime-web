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
</script>
</body>
</html>
