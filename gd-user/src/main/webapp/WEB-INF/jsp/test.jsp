<%--
  Created by IntelliJ IDEA.
  User: zhongcy
  Date: 2016/6/29
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
map:${test}
format:<fmt:formatDate value="${date}" pattern="yyyy年MM月dd日HH点mm分ss秒" />
</body>
</html>
