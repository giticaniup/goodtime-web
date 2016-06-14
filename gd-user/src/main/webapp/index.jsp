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
<a href="${pageContext.request.contextPath}/user/userRegister">用户注册</a>
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
                dataType: 'json',
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

    //将表单数据封装成对象，各个控件的name为属性名，value为属性值
    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            var val = (this.value || '').replace(/\"/g , '');
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(val);
            } else {
                o[this.name] = val;
            }
        });
        return o;
    };
</script>
</body>
</html>
