<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
这是主页。<br/>
<a href="${pageContext.request.contextPath}/item/insert">增</a>
<a href="${pageContext.request.contextPath}/item/delete">删</a>
<a href="${pageContext.request.contextPath}/item/update">改</a>
<a href="${pageContext.request.contextPath}/item/query">查</a>
</body>
</html>