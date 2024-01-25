<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버로그인</title>
</head>
<body>
<%
	String clientId = "YlfjTiqz8gzwYPd_aCT7";//애플리케이션 클라이언트 아이디값";
	String clientSecret = "0YmwxD6tYd";//애플리케이션 클라이언트 시크릿값";
	
	String code = request.getParameter("code");
	String state = request.getParameter("state");
	
	String redirectURI = URLEncoder.encode("http://192.168.0.2:8088/finalproject/snsNaverCallback.h", "UTF-8");
	
	String 	apiURL;
			apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
			apiURL += "client_id=" + clientId;
			//apiURL += "&scope=email";
			apiURL += "&client_secret=" + clientSecret;
			apiURL += "&redirect_uri=" + redirectURI;
			apiURL += "&code=" + code;
			apiURL += "&state=" + state;
	String access_token = "";
	String refresh_token = "";
	
	System.out.println("apiURL >>> : " + apiURL);
  	
	try {
    	
		URL url = new URL(apiURL);
    	HttpURLConnection con = (HttpURLConnection)url.openConnection();
    	con.setRequestMethod("GET");
    	
    	int responseCode = con.getResponseCode();

    	
    	if(responseCode==200) { // 정상 호출
    		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      		
    		String inputLine = "";
       	 	StringBuffer res = new StringBuffer();
       		
       	 	while ((inputLine = br.readLine()) != null) {
          		res.append(inputLine);
        	}
        	br.close();
        	
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject  =(JSONObject) jsonParser.parse(res.toString());
			
			access_token = (String)jsonObject.get("access_token");
			System.out.println("access_token >>> : " + access_token);
			request.setAttribute("access_token", access_token);
	
			RequestDispatcher rd = request.getRequestDispatcher("/snsNaverlogin.h");
			rd.forward(request, response);
    	} 
  	} catch (Exception e) {
    	System.out.println(e);
  	}
%>
</body>
</html>