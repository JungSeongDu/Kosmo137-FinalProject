<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kos.finalproject.funiture.vo.FpFunVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kos.finalproject.common.NumUtil" %>
<%@ page import=" org.apache.log4j.LogManager" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="java.io.*" %>
<%@ page import=" java.io.InputStreamReader" %>
<%@ page import=" java.net.HttpURLConnection" %>
<%@ page import=" java.net.URL" %>
<%@ page import=" java.net.URLEncoder" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import=" org.json.simple.parser.JSONParser" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import=" java.util.HashMap" %>
<%@ page import=" java.util.Map" %>
<%@ page import="java.security.InvalidKeyException" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page import=" java.security.SignatureException" %>
<%@ page import=" java.text.SimpleDateFormat" %>
<%@ page import=" java.util.Date" %>
<%@ page import=" java.util.Locale" %>
<%@ page import=" java.util.TimeZone" %>
<%@ page import=" java.util.UUID" %>

<%
	Logger logger = LogManager.getLogger(this.getClass());
	logger.info("funitureKart.jsp 페이지 >>> : ");
	
	// mid 값 가져오기
    String midValue = (String)request.getAttribute("mid");
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KartSelectAll</title>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript" src="/osProject/js/common.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	var midValue = '<%= midValue %>'; // JSP 변수를 JavaScript 변수로 할당
	
	$(document).on("click","#chek",function(){
		if($(this).prop('checked')){
			$('.knum').prop('checked',true);
		}else{
			$('.knum').prop('checked',false);
		}
		
	});
	
	
	
	$(document).on('click','.delBtn',function(){
		
		alert("delBtn >>> : ");
		let fnumV = $(this).val();
		alert("fnumV >>> : " + fnumV);
		
		location.href = "KartDelete.h?fnumV=" + fnumV + "&mid=" + midValue;
	});
	
	// 선택 삭제
	$(document).on('click', '#delsBtn', function(){
		kart_delete();		
	});
	
	//장바구니 비우기
	$(document).on('click','#delAllBtn',function(){
		
		kart_delete();
	});
	
	
	
	function kart_delete(){	
	
		if($('.knum:checked').length == 0){
			alert("삭제할 강의를 하나 이상 선택하세요!!")
			return;
		}
		
		var fnum = [];
		$("input:checkbox[name='fnum']:checked").each(function(){
			fnum.push($(this).val());  //체크된 것만 뽑아서 배열에 push
			console.log(fnum);
		});
		
		$('#classList').attr({
		    "action": "KartDeleteArray.h?mid=" + midValue,
		    "method": "POST"
		}).submit();
		
		}
	
	//메인화면
	$(document).on('click','#mainBtn',function(){
		 window.location.href = "http://192.168.0.2:5011/success/" + midValue;
	});
	
	
	
	
});
</script>
<style type = "text/css">
.div1{
	background : #E6E6E6;
	border : 1px;
	width : 600px;
	height : 500px;
	margin : 100px auto;
}
table {
  border-collapse: collapse;
  width: 90%;
}
.button {
  background-color: #C1C1D7;
  border: none;
  color: black;
  text-align: center;
}
</style>
</head>
<body>
<div class = "div1">
<h3 align="center">장바구니</h3>
<hr>
<form name="classList" id="classList">
<table align="center">
<thead>
<tr>
<td align = "center">
	<input type ="checkbox" name="chek" id="chek" class="chek">
</td>
<td align="center">가구번호</td>
<td align="center">가구 이름</td>
<td align="center">분위기</td>
<td align="center" >가격</td>
</tr>
</thead>
<tbody>
<%
String Knum="";
String Kname="";
String Kprice="";
String Kmood = "";
String Kpricesum="";
int sum = 0;
String sumV = "";
Object obj = request.getAttribute("kartListAll");
if(obj == null){
%>
<tr>
<td colspan="4" align="center">
	<h2>장바구니에 상품을 담으세요</h2>
</td>
</tr>
<%
}else{
		List<FpFunVO> list = (List<FpFunVO>)obj;
		int nCnt = list.size();
		
		logger.info("list.size() >>> : " + list.size());
		
		for(int i=0; i<nCnt; i++){
			
		FpFunVO fvo = list.get(i);
	    Kprice = NumUtil.comma(fvo.getKprice());
		sum += Integer.parseInt(fvo.getKprice());
%>
<tr>
<td align="center"> <input type="checkbox" name="knum" id="knum" class="knum"  value=<%= fvo.getKnum() %> > </td>

<input type="hidden" name="fnum" id="fnum" value=<%= fvo.getKnum() %> >
<td align="center" class="tt"><%= fvo.getKnum() %></td>

<input type="hidden" name="fname" id="fname" value=<%= fvo.getKname() %> >
<td align="center" class="tt"><%= fvo.getKname() %></td>

<input type="hidden" name="fmood" id="fmood" value=<%= fvo.getKmood() %> >
<td align="center" class="tt"><%= fvo.getKmood() %></td>

<input type="hidden" name="fprice" id="fprice" value=<%= fvo.getKprice() %> >
<td align="center" class="tt"> <%= Kprice %>  </td>

<td class="tt" align="center">		
		<button type="button" class="delBtn" name="delBtn" id="delBtn" value=<%= fvo.getKnum() %> >삭제</button><br>
	</td>	
</tr>
<%
		 }// end of for
} // end of else
%>


<tr>

<td colspan="4" align="left">
<input tpye="button" class="button" value="선택삭제" id= 'delsBtn' style="width:60px;">
<input tpye="button" class="button" value="장바구니 비우기" id= 'delAllBtn' style="width:100px;">
<input tpye="button" class="button" value="메인화면" id= "mainBtn" style="width:60px;">
</td>
</tr>

<tr>
<input type="hidden" name="sum" id="sum" value= <%= sum %> >
<td  align = "right"  colspan="4"><a href="Kartorder.h?mid=<%= midValue %>&sum=<%= sum %>">결제 페이지로 이동</a></td>
<td align = "right" colspan="3">총 금액  <%= sum %>원</td>
</tr>

</tbody>
</table>
</form>
</body>
</html>