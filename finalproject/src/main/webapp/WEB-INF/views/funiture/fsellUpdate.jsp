<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.kos.finalproject.funiture.vo.FpFunVO" %>
<%@ page import=" org.apache.log4j.LogManager" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="org.apache.log4j.LogManager" %>
<%@ page import="org.apache.log4j.Logger" %>   
    
 
<%


Logger logger = LogManager.getLogger(this.getClass());
logger.info("fsellUpdate.jsp 페이지 >>> : ");

//mid 값 가져오기
String midValue = (String)request.getAttribute("mid");
logger.info("midValue>>> : " + midValue);

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
		
		 var midValue = '<%= midValue %>'; // JSP 변수를 JavaScript 변수로 할당
		
		alert("jQuery ready()함수 블럭 진입 >>> : ");
		
		$(document).on("click", "#bBtn", function(){
			console.log("bBtn >>> : ");
			
			$('#boardForm').attr({
				'action': 'fsellUpdate.h?mid=<%=midValue%>',
				'method': 'GET',
				'enctype': 'multipart/form-data'
			}).submit();
		});
		
		$(document).on("click", "#dlBtn", function(){
			console.log("bBtn >>> : ");
			
			$('#boardForm').attr({
				'action': 'fsellDelete.h?mid=<%=midValue%>',
				'method': 'GET',
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
<h3>fsellUpdate.jsp</h3>
<hr>
<form name="boardForm" id="boardForm">
<table border="1" align="center">


<%

Object obj = request.getAttribute("kartListOne");
if(obj == null){


}else{
		List<FpFunVO> list = (List<FpFunVO>)obj;
		int nCnt = list.size();
		
		logger.info("list.size() >>> : " + list.size());
		
		for(int i=0; i<nCnt; i++){
			
		FpFunVO fvo = list.get(i);
	    
%>




	<tr>
		<td colspan="2" align="center">제품 정보수정</td>				
	</tr>
	
	<input type="hidden" name="mid" id="mid" value="<%= midValue %>">
	
	<td>판매자 아이디</td>
	<input type="hidden" name="fseller" id="fseller" value="<%= fvo.getFseller() %>">
	<td><%= fvo.getFseller() %></td>
	
	
	<tr>
		<td>번호</td>
		 <input type="hidden" name="fnum" id="fnum" size="20" value="<%= fvo.getFnum() %>">
		<td><%= fvo.getFnum() %></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="fname" id="fname" size="53" value="<%= fvo.getFname() %>"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input type="text" name="fprice" id="fprice" size="53" value="<%= fvo.getFprice() %>"></td>
	</tr>
	<tr>
		<td>분위기</td>
		<td><input type="text" name="fmood" id="fmood" size="20" value="<%= fvo.getFmood() %>"></td>
	</tr>
	<tr>
		<td>사진</td>
		<td>
		<input type="text" name="ffile" id="ffile" size="20" value="<%= fvo.getFfile() %>" readonly>
	</td>
	</tr>
	<tr>
	
<%
		 }// end of for
} // end of else
%>	

		<td colspan="2" align="right">				
		<input type="button" value="수정" id="bBtn">
		<input type="button" value="삭제" id="dlBtn">		
	</td>				
	</tr>
</table>
</form>
</body>
</html>