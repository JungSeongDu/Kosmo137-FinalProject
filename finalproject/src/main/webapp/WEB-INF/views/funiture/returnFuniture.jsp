<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// mid 값 가져오기
String midValue = (String)request.getAttribute("mid");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	location.href = "funiture.h?mid=" + "<%= midValue %>";
</script>
<!-- <a href="osLoginForm">aa</a> -->
</body>
</html>