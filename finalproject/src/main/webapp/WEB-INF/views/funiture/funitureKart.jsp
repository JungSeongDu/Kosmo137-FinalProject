<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.kos.finalproject.funiture.vo.FpFunVO" %> 
<%@ page import="java.util.List" %>  

<%@ page import="com.kos.finalproject.common.NumUtil" %>

<%@ page import=" org.apache.log4j.LogManager" %>
<%@ page import="org.apache.log4j.Logger" %>

<% request.setCharacterEncoding("UTF-8");%> 
<%
	Logger logger = LogManager.getLogger(this.getClass());
	logger.info("funitureKart.jsp 페이지 >>> : ");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>osKartSelectAll</title>

<script type="text/javascript" src="/osProject/js/common.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	$(document).on("click","#chek",function(){
		if($(this).prop('checked')){
			$('.fnum').prop('checked',true);
		}else{
			$('.fnum').prop('checked',false);
		}
		
	});
	
	$(document).on("click","#buyBtn",function(){
		$('#classList').attr({
			'action' : "osKartUpdate.h",
			'method' : 'POST',
			'enctype' : 'application/x-www-form-urlencoded'
		}).submit();
	});
	
	$(document).on('click','.delBtn',function(){
		
		alert("delBtn >>> : ");
		let fnumV = $(this).val();
		alert("fnumV >>> : " + fnumV);
		
		location.href = "KartDelete.h?fnumV=" + fnumV;
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
		
		$('#classList').attr({"action" : "KartDeleteArray.h","method":"GET"}).submit();
		
		}
	
	//메인화면
	$(document).on('click','#mainBtn',function(){
		location.href="fpLoginForm.h";
	});
	
});

</script>


<style type = "text/css">

.div1{
	background : #e6e6e6;
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
  background-color: #c1c1d7;
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
String Fname="";
String Fprice="";
String Fmood = "";
String Fpricesum="";
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
	    Fprice = NumUtil.comma(fvo.getFprice());
		sum += Integer.parseInt(fvo.getFprice());

%>

<tr>
<td align="center"> <input type="checkbox" name="knum" id="knum" class="knum"  value=<%= fvo.getKnum() %> > </td>

<input type="hidden" name="fnum" id="fnum" value=<%= fvo.getKnum() %> >
<td align="center" class="tt"><%= fvo.getKnum() %></td>

<input type="hidden" name="fname" id="fname" value=<%= fvo.getFname() %> >
<td align="center" class="tt"><%= fvo.getFname() %></td>


<input type="hidden" name="fmood" id="fmood" value=<%= fvo.getFmood() %> >
<td align="center" class="tt"><%= fvo.getFmood() %></td>


<input type="hidden" name="fprice" id="fprice" value=<%= fvo.getFprice() %> >
<td align="center" class="tt"> <%= Fprice %>  </td>
<td class="tt" align="center">		
		<button type="button" class="delBtn" name="delBtn" id="delBtn" value=<%= fvo.getKnum() %> >삭제</button><br>
	</td>	
</tr>
<% 
		 }// end of for
} // end of else
%>

<tr>
<td align = "right" colspan="4">총 결제 금액  <%= sum %>원</td>
</tr>


<tr>
<td colspan="4" align="right">
<input tpye="button" class="button" value="선택삭제" id= 'delsBtn' style="width:60px;">
<input tpye="button" class="button" value="장바구니 비우기" id= 'delAllBtn' style="width:100px;"> 
<input tpye="button" class="button" value="결제하기" id= "buyBtn" style="width:60px;">
<input tpye="button" class="button" value="메인화면" id= "mainBtn" style="width:60px;">
</td>
</tr>


</tbody>
</table>


</form>
</body>
</html>