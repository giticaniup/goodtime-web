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

    <title>任务看板</title>

    <!-- Bootstrap core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../../css/dashboard.css" rel="stylesheet">
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <input type="hidden" id="basePath" value="${pageContext.request.contextPath}"/>
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">任务看板</a>
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

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="${pageContext.request.contextPath}/task/todoList">任务看板 <span
                        class="sr-only">(current)
                </span></a></li>
                <li><a href="${pageContext.request.contextPath}/diary/diaryList">日志
                    <span class="sr-only">(current)</span></a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">欢迎您！
                <small>${user.userName}</small>
            </h1>
            <div class="row placeholders">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img options="size=128x128&&text=常规任务&bgcolor=#4F94CD&color=#262626" class="placeholder"/>
                    <h4>每日任务</h4>
                    <span class="text-muted">每天需要完成的小任务</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img options="size=128x128&&text=健身&bgcolor=#4F94CD&color=#262626" class="placeholder"/>
                    <h4>健身</h4>
                    <span class="text-muted">健身</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img options="size=128x128&&text=学习&bgcolor=#4F94CD&color=#262626" class="placeholder"/>
                    <h4>学习</h4>
                    <span class="text-muted">学习</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img options="size=128x128&&text=编程&bgcolor=#4F94CD&color=#262626" class="placeholder"/>
                    <h4>编程</h4>
                    <span class="text-muted">编程</span>
                </div>
            </div>
            <!-- Button trigger modal -->
            <button class = "btn btn-primary btn-lg" data-toggle = "modal" data-target = "#addTask">
                创建新任务
            </button>

            <!-- Modal -->
            <div class = "modal fade" id = "addTask" tabindex = "-1" role = "dialog"
                 aria-labelledby = "myModalLabel" aria-hidden = "true">

                <div class = "modal-dialog">
                    <div class = "modal-content">

                        <div class = "modal-header">
                            <button type = "button" class = "close" data-dismiss = "modal" aria-hidden = "true">
                                &times;
                            </button>

                            <h4 class = "modal-title" id = "myModalLabel">
                                添加新任务
                            </h4>
                        </div>

                        <div class = "modal-body">
                            <form id="addUserTask" role="form">
                                <div class="form-group">
                                    <label for="taskName">任务名称</label>
                                    <input type="text" id="taskName" name="taskName" placeholder="任务名称">
                                </div>
                                <div class="form-group">
                                    <label for="taskContent">任务内容</label>
                                    <input type="text" id="taskContent" name="taskContent" placeholder="任务内容">
                                </div>
                                <div class="form-group">
                                    <label for="taskScore">任务内容</label>
                                    <input type="number" id="taskScore" name="taskScore" placeholder="任务分数">
                                </div>
                                <div class="form-group">
                                    <label for="beginTime">开始时间</label>
                                    <input size="16" id="beginTime" type="text" name="beginTime" value="2016-06-15 14:45"
                                            class="form_datetime">
                                </div>
                                <div class="form-group">
                                <label for="endTime">结束时间</label>
                                <input size="16" id="endTime" type="text" name="endTime" value="2016-06-15 14:45"
                                       class="form_datetime">
                                </div>
                                <div hidden="true" id="addUserTaskWarning" class="alert alert-warning">
                                    <a href="#" class="close" onclick="$('#addUserTaskWarning').hide()">
                                        &times;
                                    </a>
                                    <strong>提示：</strong>参数输入错误
                                </div>
                            </form>
                        </div>

                        <div class = "modal-footer">
                            <button type = "button" class = "btn btn-default" data-dismiss = "modal">
                                关闭
                            </button>

                            <button id="addTaskButton" type = "button" class = "btn btn-primary" to>
                                提交
                            </button>
                        </div>

                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->

            </div><!-- /.modal -->
            <h2 class="sub-header">常规任务看板</h2>
                <div class="table-responsive">
                    <table id="tasktable" class="table table-striped">
                        <thead>
                        <tr>
                            <th>任务Id</th>
                            <th>任务名称</th>
                            <th>任务内容</th>
                            <th>开始日期</th>
                            <th>结束日期</th>
                        </tr>
                        </thead>
                        <tbody id="tasktBody">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
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

    <script type="text/javascript" src="../../js/usertask.js"></script>
</body>
</html>
