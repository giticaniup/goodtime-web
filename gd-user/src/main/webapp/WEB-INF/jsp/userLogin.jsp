<%--suppress ALL --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>用户登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap core CSS -->
    <link href="../../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../../css/signin.css" rel="stylesheet">
</head>
<body>

<input type="hidden" id="basePath" value="${pageContext.request.contextPath}"/>
<div class="container">

    <form class="form-signin">
        <h2 class="form-signin-heading">欢迎使用GoodTime</h2>
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputUserId" class="sr-only">Email address</label>
        <input type="id" id="inputUserId" class="form-control" placeholder="UserId" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <div hidden="true" id="userLogin" class="alert alert-warning">
            <a href="#" class="close" onclick="$('#userLogin').hide()">
                &times;
            </a>
            <strong>提示：</strong>用户名或密码错误
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" onclick="userLogin()">Sign in</button>
    </form>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../bootstrap/js/ie10-viewport-bug-workaround.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="../../js/userLogin.js"></script>
</body>
</html>
