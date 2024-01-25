package com.kos.finalproject.sns.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kos.finalproject.sns.vo.SnsLoginVO;

@Controller
public class SnsLoginController {
	Logger logger = LogManager.getLogger(SnsLoginController.class);
	
	
	// 로긴 폼
	@RequestMapping("snsloginForm")
	public String kosLoginForm() {
		logger.info("fpLoginForm 함수 진입 >>> : ");	
		return "main/fpLoginForm";
	}
	
	// kakao Login ========================================================================================	 	
		@RequestMapping("snskakaoLogin")
		public String kakaoLogin(HttpServletRequest req, SnsLoginVO svo, Model model) {
			logger.info("kakaoLogin 함수 진입 성공 >>> : ");
			
			String snstype = req.getParameter("snstype");
			String snsid = req.getParameter("snsid");
			String nickname = req.getParameter("nickname");
			String snsemail = req.getParameter("snsemail");
			
			logger.info("snstype >>> : " + snstype);
			logger.info("snsid >>> : " + snsid);
			logger.info("snsemail >>> : " + snsemail);
			
			svo.setSnstype(snstype); // snstype :: 01:카카오, 02:네이버
			svo.setSnsid(snsid);
			svo.setSnsemail(snsemail);
			
			
			// sns 입력시 기존 not null 컬럼을 처리하기 
			String mid = snsid;
			//String mpw = "snspw_test";
			//String mhp = "01012341234";
			//String memail = snsemail;
			
			svo.setMid(mid);
			//svo.setMpw(mpw);
			//svo.setMhp(mhp);
			//svo.setMemail(memail);
			
			// int snsLogin = kosLoginService.kakaoInsert(kvo);
			
			// 로직 처리하기 
			// if (snsLogin == 1) {
				
				
			// }else {
				
			// }
			
			return "redirect:http://192.168.0.2:5011/success/"+snsemail;
		}
		
		
		// naver Login ========================================================================================	 
		@RequestMapping("snsNaverCallback")
		public String naverCallback() {
			logger.info("naverCallback 함수 진입 성공 >>> :");
			
			return "main/naverCallback";
		}
		
		@RequestMapping("snsNaverlogin")
		public String naverLogin(HttpServletRequest request, Model model) {
			logger.info("naverLogin 함수 진입 성공 >>> : ");

			String access_token = (String) request.getAttribute("access_token");
			logger.info("naverLogin access_token >>> : " + access_token);
			
			String token = access_token;
			String header = "Bearer " + token;
			//String apiURL = "https://openapi.naver.com/v1/nid/me";
			String apiURL = "https://openapi.naver.com/v1/nid/me?scope=email";
			Map<String, String> requestHeaders = new HashMap<>();
			requestHeaders.put("Authorization", header);
			String responseBody = get(apiURL, requestHeaders);
			logger.info("responseBody >>> : " + responseBody);
			
	        //위에 값을 콘솔로 찍어본다. name 값이 유니코드인데 브라우저에서 자동으로 변환해서 읽고 json simple 라이브러리가 변환해준다.
			
	        JSONObject jobj = new JSONObject();
	        JSONParser parser = new JSONParser();

	        try {
				jobj = (JSONObject)parser.parse(responseBody);
				
				// resultCode가 00이고 message가 success이면 실행
				String resultcode = (String)jobj.get("resultcode");
				String message = (String)jobj.get("message");
				
				jobj =(JSONObject)jobj.get("response"); 			//여기서 response가 json객체 안에 json객체
				String mid =(String)jobj.get("id");					// 아이디 1x 
				String email =(String)jobj.get("email");			// 이메일 
				//String name =(String)jobj.get("name");				// 이름 
				//String nick = (String)jobj.get("nickname");			// 닉네임
				//String phone = (String)jobj.get("mobile");			// 전화번호
				//String photo = (String)jobj.get("profile_image");
				
				logger.info("id >>> : " + mid);
				logger.info("email >>> : " + email);
				//logger.info("name >>> : " + name);
				//logger.info("nick >>> : " + nick);
				//logger.info("phone >>> : " + phone);
				//logger.info("photo >>> : " + photo);
		
				// VO 이용해서 로그인 루틴 만들기 
				
				return "redirect:http://192.168.0.2:5011/success/"+email;
				
	        } catch (Exception e) {
				System.out.println("json 객체 변환실패");		
			}
			       
	       return "main/fpLoginForm";
		}
		
		
		
//		-------- RestFul 방식의 데이터 받아오기 위한 메소드 -------------------------------------------------------------------
		public static String get(String apiUrl, Map<String, String> requestHeaders) {
			HttpURLConnection con = connect(apiUrl);
			try {
				con.setRequestMethod("GET");
				for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
					con.setRequestProperty(header.getKey(), header.getValue());
				}
				int responseCode = con.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
					return readBody(con.getInputStream());
				} else { // 에러 발생
					return readBody(con.getErrorStream());
				}
			} catch (IOException e) {
				throw new RuntimeException("API 요청과 응답 실패", e);
			} finally {
				con.disconnect();
			}
		}
		
		public static HttpURLConnection connect(String apiUrl) {
			try {
				URL url = new URL(apiUrl);
				return (HttpURLConnection) url.openConnection();
			} catch (MalformedURLException e) {
				throw new RuntimeException("API URL이 잘못되었습니다 >>> : " + apiUrl, e);
			} catch (IOException e) {
				throw new RuntimeException("연결이 실패했습니다 >>> : " + apiUrl, e);
			}
		}
		
		public static String readBody(InputStream body) {
			InputStreamReader streamReader = new InputStreamReader(body);
			try (BufferedReader lineReader = new BufferedReader(streamReader)) {
				StringBuilder responseBody = new StringBuilder();
				String line;
				while ((line = lineReader.readLine()) != null) {
					responseBody.append(line);
				}
				return responseBody.toString();
			} catch (IOException e) {
				throw new RuntimeException("API 응답을 읽는데 실패했습니다. >>> : ", e);
			}
		}	
	}
