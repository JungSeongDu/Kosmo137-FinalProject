<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.kos.finalproject.funiture.vo.FpFunVO" %>
<%@ page import=" org.apache.log4j.LogManager" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="org.apache.log4j.LogManager" %>
<%@ page import="org.apache.log4j.Logger" %>
<% request.setCharacterEncoding("UTF-8");%>
<%
   Logger logger = LogManager.getLogger(this.getClass());
   logger.info("fpFuniture.jsp 페이지 >>> : ");
   
   
   //mid 값 가져오기
   String midValue = (String)request.getAttribute("mid");
   
   
   //페이징 변수 세팅
      int pageSize = 0;
      int groupSize = 0;
      int curPage = 0;
      int totalCount = 0;
     
      
      String url = "";
   Object objPaging = request.getAttribute("pagingKBVO");
   FpFunVO pagingKBVO = (FpFunVO)objPaging;
   Object obj = request.getAttribute("listAll");
  List<FpFunVO> list = (List<FpFunVO>)obj;
  
  
  int nCnt = list.size();
  
   if(list.size() > 0){
   String nCntS = ":::: 전체 조회 건수  " + nCnt + " 건";
   String snb = "강의상세보기";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
  table {
  position: absolute;
  top:10%;
  left:20%;
      border-collapse: collapse;
  }
  tr:last-child {
    border-bottom: 1px solid black; /* 원하는 선의 스타일과 색상을 지정하세요 */
  }
  td, th {
    padding: 20px; /* 모든 셀에 적용되는 내용과 셀 경계 사이의 간격 */
  }
  h1{
  top:3%;
  	position: absolute;
  	left: 46%;
  }
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
	   var midValue = '<%= midValue %>'; // JSP 변수를 JavaScript 변수로 할당
	  
      //메인화면
      $(document).on('click','#homeButton',function(){
         //location.href="fpLoginForm.h";
    	  window.location.href = "http://192.168.0.2:5011/success/" + midValue;
      });
    //장바구니 등록
      $(document).on('click','#selectone',function(){
    	  $('#kart').attr({
  			'action' : "fpInsert.h?mid=" + midValue,
  			'method' : 'POST',
  			'enctype' : 'application/x-www-form-urlencoded'
  		}).submit();
    	  alert("장바구니 등록 완료")
      });
    //장바구니 전체 조회
      $(document).on('click','#full',function(){
         //location.href="kartSelectAll.h";
    	  window.location.href = "kartSelectAll.h?mid=" + midValue;
      });
   });
</script>
</head>
<body>
<h1>Funiture All 페이지</h1>
<form name="funitureall" id="funitureall">
<table align="center" style="width: 60%; cellpadding: 20px;">
<thead>
<tr>
   <th></th>
   <th>   </th>
</tr>
</thead>
<%
for(int i=0; i<nCnt; i++){
	FpFunVO fvo = list.get(i);
   // 페이징 세팅
   pageSize = Integer.parseInt(pagingKBVO.getPageSize());
   groupSize = Integer.parseInt(pagingKBVO.getGroupSize());
   curPage = Integer.parseInt(pagingKBVO.getCurPage());
   totalCount = Integer.parseInt(fvo.getTotalCount());
%>
<tbody>
<form name="kart" id="kart">
<tr>
  <input type="hidden" name="mid" id="mid" value="<%= midValue %>">
  <td style="width: 200px;"><img src="<%= fvo.getFfile() %>" alt="이미지" style="max-width: 100%; height: auto;"></td>	
	
  <td colspan='10' style="width: 100px;"><span style="font-size: 20px;">
  
  <input type="hidden" name="fnum" id="fnum" value="<%= fvo.getFnum() %>">
   번호 :	<%= fvo.getFnum() %><br><br>
   
  <input type="hidden" name="fname" id="fname" value="<%= fvo.getFname() %>">
   가구이름: <%= fvo.getFname() %><br><br>
   
  <input type="hidden" name="fmood" id="fmood" value="<%= fvo.getFmood() %>">
    분위기: <%= fvo.getFmood() %><br><br>
    
  <input type="hidden" name="fprice" id="fprice" value="<%= fvo.getFprice() %>">
    가격:  <%= fvo.getFprice() %>원<br><br>
    
  <input type="button" name="selectone" id="selectone" value="장바구니" style="width: 100px; height: 40px;">
</tr>
</form>
<%
   }
if(true){
	 
	} else { }
	
%>
<tr>
   <td colspan="7">
      	<jsp:include page="funiturePaging.jsp" flush="true">
         <jsp:param name="str" value=""/>
         <jsp:param name="url" value='<%= url %>'/>
         <jsp:param name="pageSize" value="<%=pageSize%>"/>
         <jsp:param name="groupSize" value="<%=groupSize%>"/>
         <jsp:param name="curPage" value="<%=curPage%>"/>
         <jsp:param name="totalCount" value="<%=totalCount%>"/>
         <jsp:param name="mid" value="<%=midValue%>"/>
      </jsp:include>
   </td>
</tr>
<% } %>
</tbody>
<tfoot>
    <tr>
      <td colspan="10" align='right'>
        <input type="button" name="homeButton" id="homeButton" value="메인페이지" style="width: 100px; height: 40px;">
        <input type="button" name="full" id="full" value="바구니 조회" style="width: 100px; height: 40px;">
      </td>
    </tr>
  </tfoot>
</table>
</form>
</body>
</html>