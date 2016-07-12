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
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="/css/blog.css" rel="stylesheet">
</head>
<body>
    <form role="form" action="/diary/saveDiary" class="form-mid-group">
        <div>
            <label for="title">标题</label>
            <input id="title" name="title" type="text" style="width:70%" class="form-control"
                   placeholder="输入标题" align="center" >
            <label for="content">日志内容</label>
        <textarea id="content" name="content" class="form-control" style="width:70%" placeholder="输入正文"
                  rows="20" align="center" ></textarea>
        </div>
        <button id="fat-btn" class="btn btn-primary" type="submit">保存
        </button>
    </form>
    <footer class="blog-footer">
        <p><a href="https://github.com/giticaniup/goodtime2.git">工程源码</a> by 钟乘永.</p>
    </footer>
<script src="/js/jquery-1.12.3.min.js"></script>
</body>
</html>
