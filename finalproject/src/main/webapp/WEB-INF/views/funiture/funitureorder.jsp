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
// mid 값 가져오기
String sum = (String)request.getAttribute("sum");
String mid = (String)request.getAttribute("mid");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript" src="/osProject/js/common.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">



$(document).ready(function(){
	
	sum = <%= sum %>;
	
	
	$(document).on("click", "#buyBtn", function(){
		
        // 결제 정보 설정
        var paymentData = {
            pg: 'html5_inicis',
            pay_method: 'card',
            merchant_uid: 'merchant_' + new Date().getTime(),
            amount: sum , // 결제할 총 금액을 동적으로 할당
            name: '장바구니 상품 결제',
            buyer_email: 'buyer@example.com',
            buyer_name: '구매자 이름',
            buyer_tel: '010-1234-5678',
            buyer_addr: '서울시 강남구',
            buyer_postcode: '123-456'
        };

        // Iamport 결제창 열기
        IMP.init('imp23814146'); // 실제로는 발급받은 Iamport 키를 입력해야 합니다.
        IMP.request_pay(paymentData, function(response) {
            if (response.success) {
                // 결제 성공 시 처리
                alert('결제가 완료되었습니다.');
                // 여기에 결제 성공 시의 추가 로직을 작성할 수 있습니다.
                // 예를 들어 결제 완료 후 서버에 결제 정보를 전송하는 등의 동작을 수행할 수 있습니다.
            } else {
                // 결제 실패 시 처리
                var errMsg = '결제에 실패하였습니다. ' + response.error_msg;
                alert(errMsg);
            }
        });
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
<form>
<body>

<h1>결제 확인 페이지</h1>  
<tr>
<h3>총 결제 금액  <%= sum %>원</h3>
<input tpye="button" class="button" value="결제하기" id= "buyBtn" style="width:60px;">
</tr>
  
<a href = "funiture.h?mid=<%= mid %>">쇼핑 계속하기</a>

</body>
</form>
</html>