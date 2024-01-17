<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

   String mid ="";
   String mpw ="";
   Cookie ck[] = request.getCookies();
   if(ck !=null && ck.length > 0){
      
      for(int i=0; i < ck.length; i++){
         
         if (ck[i].getName().equals("ID")){
            mid = ck[i].getValue();
         } else if(ck[i].getName().equals("PW")){
            mpw = ck[i].getValue();
         }
      }
   }
   
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>osLoginForm</title>


<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

   $("#mid").attr('placeholder','아이디');
   $("#mpw").attr('placeholder','비밀번호');
   
   $("#btn").click(function(){
      console.log("btn >>> : ");
      auto_login();
      
      $('#fploginForm').attr({
         'action' : "login.h",
         'method' : 'POST',
         'enctype' : 'application/x-www-form-urlencoded'
      }).submit();

   });

   function auto_login(){
   if ($('.chek:checked').length == 1){
         alert("자동로그인 정보 입력완료");
      return;
   }}
});

</script>


</head>
<body>
<div class = "div1">
<hr>
<h1 align="center">FP로그인</h1>
<hr>
<form name = "fploginForm" id="fploginForm">
<table border="0" align="center">

<tr>
<th>ID</th>
<td><input type="text" name="mid" id="mid" style="width:150px;" value="<%= mid %>"></td>
</tr>

<tr>
<th>PASSWORD</th>
<td><input type="password" name="mpw" id="mpw" style="width:150px;" value="<%= mpw %>"></td>
</tr>

<tr>
<td style="text-align: center;" colspan="2">
<button type="button" id="btn" style="width:70px; height:40px;">LOGIN</button>
</td>
</tr>
</body>
</html>