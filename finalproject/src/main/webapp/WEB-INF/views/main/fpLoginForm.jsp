<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    String clientId = "YlfjTiqz8gzwYPd_aCT7";	
    String redirectURI = URLEncoder.encode("http://192.168.0.8:8088/finalproject/snsNaverCallback.h", "UTF-8");
    SecureRandom random = new SecureRandom();	
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
                   + "&client_id=" + clientId
                   + "&redirect_uri=" + redirectURI
                   + "&state=" + state;
    session.setAttribute("state", state);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>osLoginForm</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">
    window.Kakao.init('de86ae9726833f684f74abf106ff13b2');
    function kakaoLoginApi() {
        window.Kakao.Auth.login({
            success: function(authObj){
                window.Kakao.API.request({
                    url: '/v2/user/me',
                    success : function(res) {
                        let k_id = res.id;
                        let k_email = res.kakao_account.email;
                        let k_nickname = res.properties.nickname;
                        kakaoLogin(k_id,k_nickname,k_email);
                    }
                });				
            }
        });
    }
    function kakaoLogin(k_id,k_nickname,k_email) {
        location.href="/finalproject/snskakaoLogin.h?snstype=01&snsid="+k_id+"&nickname="+k_nickname+"&snsemail="+k_email;
    }
    $(document).ready(function(){
       $("#mid, #mpw, #searchvalue").css({
            "height": "28px", // 세로 폭 조정
            "background-color": "#d9ecff"
       });
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
       $("#searchvalue").attr('placeholder','분위기 검색'); // 플레이스홀더 추가
    });
</script>
<style>
    body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            flex-direction: column;
            background:
            linear-gradient(135deg, #6482B9 21px, #d9ecff 22px, #d9ecff 24px, transparent 24px, transparent 67px, #d9ecff 67px, #d9ecff 69px, transparent 69px),
            linear-gradient(225deg, #6482B9 21px, #d9ecff 22px, #d9ecff 24px, transparent 24px, transparent 67px, #d9ecff 67px, #d9ecff 69px, transparent 69px)0 64px;
            background-color:#6482B9;
            background-size: 64px 128px;
            }
    .login-container {
        width: 500px; /* 가로 크기 유지 */
        height: 370px; /* 세로 크기를 400px로 조정 */
        border: 3px solid #243682;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        margin: auto;
        padding: 20px;
        position: relative;
        background-color: rgba(112, 128, 144, 0.88);
    }
    form, .login-actions {
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 10px 0;
    }
    .login-actions {
        margin-top: 80px; /* SNS 로그인 버튼을 아래로 30px 더 내림 */
    }
    input[type=text], input[type=password] {
        width: 90px;
        padding: 5px;
        margin-right: 3px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    button {
        background-color: #243682;
        border: 2px solid ivory;
        color: white;
        font-weight: bold;
        border-radius: 5px;
        padding: 10px 20px;
        cursor: pointer;
        transition: background-color 0.3s ease;
        margin-bottom: 10px;
    }
    button:hover {
        background-color: #1b2e5f;
    }
    .kakao-login {
        background-color: #FEE500;
        color: black;
    }
    .naver-login {
        background-color: #03C75A;
    }
    #searchForm {
        position: absolute;
        top: 20px;
        right: 20px;
    }
    .intro-link {
    background-color: #6482B9; 
    color: white; 
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    margin-top: 10px; 
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.intro-link:hover {
    background-color: #5068A9; 
}

    .home-link {
        position: absolute;
        top: -45px; /* 컨테이너 상단으로부터 15px 위로 설정 */
        left: 50%; /* 좌우 중앙 정렬 */
        transform: translateX(-50%); /* 정확한 중앙 정렬을 위해 X축 기준으로 -50% 이동 */
    }
    
    .intro-link {
        background-color: #6482B9; 
        color: white; 
        border: 3px solid #243682; /* 진한 남색 테두리 */
        border-radius: 5px;
        padding: 10px 20px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    
    .intro-link:hover {
        background-color: #5068A9;
    }
</style>
</style>
</head>
<body>
<div class="login-container">
    <h2 align="center">🛋️집에 가구 싶다🛏️</h2>
    <form name="searchForm" id="searchForm">
        <input type="text" name="searchvalue" id="searchvalue" style="width:150px;">
        <button type="submit" id="submit">검색</button>
    </form>
    <form name="fploginForm" id="fploginForm">
        <input type="text" name="mid" id="mid" value="<%= mid %>">
        <input type="password" name="mpw" id="mpw" value="<%= mpw %>">
        <button type="button" id="btn">LOGIN</button>
    </form>
    <div class="login-actions">
        <button onclick="kakaoLoginApi()" class="kakao-login">카카오 로그인</button>
        <button onclick="location.href='<%= apiURL %>'" class="naver-login">네이버 로그인</button>
    </div>
    <div class="home-link">
    <button onclick="location.href='websiteIntroduce'" class="intro-link">우리 홈페이지는요</button>
</div>
</div>
</body>
</html>
