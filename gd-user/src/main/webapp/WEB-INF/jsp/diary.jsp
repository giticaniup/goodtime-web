<!DOCTYPE html>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>日志</title>

    <!-- Bootstrap core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="../../css/blog.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../../css/dashboard.css" rel="stylesheet">
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <input type="hidden" id="basePath" value="${pageContext.request.contextPath}"/>
    <input type="hidden" id="year" value="${year}"/>
    <input type="hidden" id="month" value="${month}"/>
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/task/todoList">任务看板</a>
            <a class="navbar-brand" href="#">日志看板</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/logout">登出</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>
<div class="blog-masthead">
    <div class="container">
        <nav class="blog-nav">
            <a class="blog-nav-item active" href="#">首页</a>
            <a class="blog-nav-item" href="/diary/addDiary">新的日志</a>
            <a class="blog-nav-item" href="#">管理</a>
            <a class="blog-nav-item" href="#">关于</a>
        </nav>
    </div>
</div>
<div class="container">

    <div class="blog-header">
        <h1 class="blog-title">${user.userName}
            <small>的个人日志</small>
        </h1>
        <p class="lead blog-description">下马饮君酒，问君和所之。君言志四海，天涯若比邻。</p>
    </div>

    <div class="row">

        <div class="col-sm-8 blog-main" >

            <div id="blogMain">
            </div>
            <nav>
                <ul class="pager">
                    <li><a id="previousPage" href="#">Previous</a></li>
                    <li><a id="nextPage" href="#">Next</a></li>
                </ul>
            </nav>
        </div><!-- /.blog-main -->
        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
            <div class="sidebar-module sidebar-module-inset">
                <h4>关于</h4>
                <p>暂时空缺</p>
            </div>
            <div class="sidebar-module">
                <h4>Archives</h4>
                <ol class="list-unstyled" id="ol_date">
                </ol>
            </div>
        </div><!-- /.blog-sidebar -->

    </div><!-- /.row -->

</div><!-- /.container -->

<footer class="blog-footer">
    <p>Blog template built for <a href="http://getbootstrap.com">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
    <p>
        <a href="#">返回顶部</a>
    </p>
</footer>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../bootstrap/js/ie10-viewport-bug-workaround.js"></script>
<!--cookie操作-->
<script src="../../bootstrap/js/jquery.cookie.min.js"></script>
<script src="../../bootstrap/js/placeholder.min.js"></script>
<script src="../../bootstrap/js/bootstrap-datetimepicker.js"></script>
<script src="../../bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../../js/JSONFormatter.js"></script>

<script type="text/javascript" src="../../js/userDiary.js"></script>
</body>
</html>
