<%--
  Created by IntelliJ IDEA.
  User: zhongcy
  Date: 2016/6/27
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增日志</title>
    <!-- Bootstrap core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body>
<form role="form" action="/diary/saveDiary">
    <div class="form-group">
        <label for="title">标题</label>
        <input id="title" name="title" type="text" style="width:60%" class="form-control" placeholder="文本输入">
        <label for="content">日志内容</label>
        <textarea id="content" name="content" class="form-control" style="width:60%" placeholder="文本输入"
                  rows="10"></textarea>
    </div>
    <button id="fat-btn" class="btn btn-primary" type="submit">保存
    </button>
</form>
<script src="/js/jquery-1.12.3.min.js"></script>
</body>
</html>
