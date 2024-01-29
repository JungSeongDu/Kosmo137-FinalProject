<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
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
<%
	//네이버 로그인 =============================================================================================	
	String clientId = "YlfjTiqz8gzwYPd_aCT7";//애플리케이션 클라이언트 아이디값";	
	String redirectURI = URLEncoder.encode("http://192.168.0.2:8088/finalproject/snsNaverCallback.h", "UTF-8");
	
	SecureRandom random = new SecureRandom();	
	String state = new BigInteger(130, random).toString();
	
	String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		   apiURL += "&client_id=" + clientId;
		   //apiURL += "&scope=email";
		   apiURL += "&redirect_uri=" + redirectURI;
		   apiURL += "&state=" + state;
	System.out.println("apiURL >>> : " + apiURL);		  
	session.setAttribute("state", state);
	
	//네이버 로그인 ==============================================================================================
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>osLoginForm</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- 카카오 로그인 api  -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">
//카카오 로그인 =====================================================================================
	window.Kakao.init('de86ae9726833f684f74abf106ff13b2'); // 사용하려는 앱의 JavaScript 키 입력
	
	function kakaoLoginApi() {
		alert("kakaoLoginApi >>> : ");
		
		window.Kakao.Auth.login({
			success: function(authObj){
			
				// console.log( "authObj >>> : " + JSON.stringify(authObj));					
				window.Kakao.API.request({
					
					url: '/v2/user/me',
					
					success : function(res) {
						console.log( "res  >>> : " + JSON.stringify(res));
	                       
                        let k_id = res.id;
                        let k_email = res.kakao_account.email;
                        //let k_gender = res.kakao_account.gender;
                        let k_nickname = res.properties.nickname;
                        //let k_profile_image = res.properties.profile_image;
                        // let k_thumbnail_image = res.properties.thumbnail_image;
                        console.log("k_id >>> : " + k_id);
                        console.log("k_email >>> : " + k_email);
                        //console.log("k_gender >>> : " + k_gender);
                        console.log("k_nickname >>> : " + k_nickname);
                        //console.log("k_profile_image >>> : " + k_profile_image);
                        // console.log("k_thumbnail_image >>> : " + k_thumbnail_image);
                        //kakaoLogin(k_id, k_email);
                        kakaoLogin(k_id,k_nickname,k_email);
					}
				});				
			}
		});
	}
	
	function kakaoLogin(k_id,k_nickname,k_email) {
		alert("kakaoLogin >>> : " + k_id );
		
		// Ajax 호출해서 로직 만들기 대신 자바스크립트 location 이용해보기
		// 학생들은 코딩할 때는 Ajax 로 하기
		location.href="/finalproject/snskakaoLogin.h?snstype=01&snsid="+k_id+"&nickname="+k_nickname+"&snsemail="+k_email;
	}
//카카오 로그인 =======================================================================================
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
   //검색기능
   $("#submit").click(function(){
       $('#searchForm').attr({
           "action": "moodSelectMain.h",
           "method": "GET"
       });
       form.submit();
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
<form name = "searchForm" id="searchForm">
<tr>
<th>분위기 검색</th>
<td><input type="text" name="searchvalue" id="searchvalue" style="width:150px;" ></td>
<button type="submit" id="submit">검색</button>
</tr>
</form>
<!-- <a href="funiture.h">가구 구매</a>  -->
<!-- http://localhost:8088/finalproject/funiture.h -->
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
<tr>
<td>
<a href="javascript:kakaoLoginApi()">카카오 로그인</a>
</td>
<td>
<a href="<%= apiURL %>">네이버 로그인</a>
</td>
</tr>
</table>
</form>
</body>
</html>







