<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kos.finalproject.funiture.vo.FpFunVO" %>
<%

//mid 값 가져오기
String midValue = (String)request.getAttribute("mid");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품등록 </title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	alert("자바스크립트 블럭 진입 >>> : ");
	
	$(document).ready(function(){
		alert("jQuery ready()함수 블럭 진입 >>> : ");
		
		$(document).on("click", "#bBtn", function(){
			console.log("bBtn >>> : ");
			
			$('#boardForm').attr({
				'action': 'fsell.h?mid=<%=midValue%>',
				'method': 'POST',
				'enctype': 'multipart/form-data'
			}).submit();
		});
	});	
</script>
<style type="text/css">

	h3 {
		text-align: center;
	}
	
	.tt {
		text-align: center;
	}
</style>
</head>
<body>
<h3>boardForm.jsp</h3>
<hr>
<form name="boardForm" id="boardForm">
<table border="1" align="center">
	<tr>
		<td colspan="2" align="center">제품 등록</td>				
	</tr>
	
	<tr>
		<td>판매자 아이디</td>
		<input type="hidden" name="fseller" id="fseller" value="<%= midValue %>">
		<td><%=  midValue %></td>
	</tr>
	
	<tr>
		<td>번호</td>
		<td><input type="text" name="fnum" id="fnum" size="20" readonly></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="fname" id="fname" size="53"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input type="text" name="fprice" id="fprice" size="53"></td>
	</tr>
	<tr>
		<td>분위기</td>
		<td><input type="text" name="fmood" id="fmood" size="20"></td>
	</tr>
	<tr>
		<td>사진</td>
		<td>
		<input type="file" name="ffile" id="ffile">
	</td>
	</tr>
	<tr>
		<td colspan="2" align="right">				
		<input type="button" value="등록" id="bBtn">		
	</td>				
	</tr>
</table>
</form>
</body>
</html>