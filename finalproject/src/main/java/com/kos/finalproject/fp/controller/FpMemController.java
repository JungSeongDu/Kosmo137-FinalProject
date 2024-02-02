package com.kos.finalproject.fp.controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kos.finalproject.fp.vo.FpMemVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.kos.finalproject.fp.service.FpMemService;

@Controller
public class FpMemController{
	Logger logger = LogManager.getLogger(FpMemController.class);
	   //서비스 연결
	   @Autowired
	   private FpMemService fpMemService;
	
	   //레디스
	   @Autowired
	   private JedisPool jedisPool;

	
	//로그인폼
	   @GetMapping(value= "fpLoginForm")
	   //@GetMapping("fpLoginForm")
	   public String loginForm() {
	      logger.info("FpLoginController loginForm() 함수 진입 >>> : ");
	      return"main/fpLoginForm";
	   }
	  
	 /*
	   //로그인
	   @RequestMapping(value = "login",method=RequestMethod.POST)
	   //@GetMapping("login" )
	   public String login(HttpServletResponse response, HttpServletRequest req, FpMemVO fvo, Model model) {
	      logger.info("login() 함수 진입 >>> : ");
	      logger.info("login() 함수 진입 >>> : " + fvo.getMid());
	      logger.info("login() 함수 진입 >>> : " + fvo.getMpw());
	      String mid = fvo.getMid();
	      String mpw = fvo.getMpw();
	    
	    
	     
	      List<FpMemVO> listLogin = fpMemService.loginCheck(fvo);
	      logger.info("list size >>> " + listLogin.size());
	     
	      if (listLogin.size() == 1) {
	         model.addAttribute("listLogin", listLogin);
	        
	        
	         return "redirect:http://192.168.0.2:5011/success";
	      }
	      return "main/fpLoginForm";
	   }
	  
	   */
	  
	   @CrossOrigin(origins = "http://192.168.0.2:5011") // 허용할 오리진을 명시
	    @PostMapping(value = "login")
	    public String login(HttpServletResponse response, HttpServletRequest req, FpMemVO fvo, Model model) {
	        // Your existing code
		   	  logger.info("login() 함수 진입 >>> : ");
		      logger.info("login() 함수 진입 >>> : " + fvo.getMid());
		      logger.info("login() 함수 진입 >>> : " + fvo.getMpw());
		      String mid = fvo.getMid();
		      String mpw = fvo.getMpw();	
		  
		      //HttpSession session = request.getSession(true);      // HttpServletRequest에서 세션을 가져오거나 새로 생성
		      //String sessionId = session.getMid();       // 세션에서 고유한 세션 아이디 가져오기
		      
	        List<FpMemVO> listLogin = fpMemService.loginCheck(fvo);
	        
	        /*
	        try (Jedis jedis = jedisPool.getResource()) {
                
                // Redis에 데이터 저장
                  jedis.set( sessionId, adminyn);
                  // Redis 만료 시간 설정 (3600=1시간)
                  jedis.expire(sessionId, 3600*24);
                  logger.info("jedis.set >>> : ");
              }
              */

	        
	        if (listLogin.size() == 1) {
	            model.addAttribute("listLogin", listLogin);
	            return "redirect:http://192.168.0.2:5011/success/"+mid;
	        }
	        return "main/fpLoginForm";
	    }
	  
	  
	  
	// 로그 아웃
	   @GetMapping(value="logout")
	   public String logout() {
	      logger.info("OsLoginController logout() 함수 진입 >>> : ");
	        
	      return "main/fpLoginForm";
	   }
	  
	  
	// 업데이트 폼
	   @GetMapping(value="fpUpdateForm")
	   public String loginform() {
	      logger.info("OsLoginController fpUpdateForm() 함수 진입 >>> : ");
	        
	      return "main/fpUpdateForm";
	   }
	  
	  
	//회원정보 수정
	   @GetMapping(value="memUpdate")
       public String osMypageUpdate(HttpServletRequest req, FpMemVO fvo, Model model) {
          logger.info("memUpdate >>> :");
          logger.info("memUpdate 함수 진입 fvo.getMid() >>> : " + fvo.getMid());
          int nCnt = fpMemService.memUpdate(fvo);
          if (nCnt > 0) {
             logger.info("fpMemService nCnt >>> : " + nCnt);
             return "main/fpLoginForm";
          }
          return "main/fpUpdateForm";
       }
	  
	  
	//회원 탈퇴
	   @GetMapping(value = "memDelete")
       public String osMypageDelete(FpMemVO fvo, Model model) {
          logger.info("memDelete >>> :");
          logger.info("memDelete 함수 진입 fvo.getMid() >>> : " + fvo.getMid());
          int nCnt = fpMemService.memDelete(fvo);
          if (nCnt > 0) {
             logger.info("fpMemService nCnt >>> : " + nCnt);
             return "main/fpLoginForm";
          }
          return "main/fail";
       }
	  
	 //회원가입 폼
	   @GetMapping(value= "fpInsertForm")
	   //@GetMapping("fpLoginForm")
	   public String fpInsertForm() {
	      logger.info("FpLoginController fpInsertForm() 함수 진입 >>> : ");
	      return"main/fpInsertForm";
	   }
	  
	  
	 //회원가입
	   @GetMapping(value="fpMemInsert")
	   public String osMemInsert(FpMemVO fvo, Model model) {
	      logger.info(" osMemInsert 함수 진입 >>> : ");
	      logger.info(" getMid() >>> : " + fvo.getMid());
	      logger.info(" getMpw() >>> : " + fvo.getMpw());
	      logger.info(" getMname() >>> : " + fvo.getMname());
	     
	     
	      int insertCnt = fpMemService.fpMemInsert(fvo);
	      if(insertCnt > 0){
	         logger.info("insertCnt >>> : " + insertCnt);
	         model.addAttribute("insertCnt", insertCnt);
	         return "main/fpLoginForm";
	      }
	     
	   return "main/fpInsertForm";
	     
	}
	  
	 //아이디중복체크
	   @PostMapping(value="idCheck")
	   @ResponseBody
	   public Object idCheck(FpMemVO fvo) {
	      logger.info("idCheck 함수 진입 >>> :");
	      logger.info("idCheck fvo.getmid()() >>> : " + fvo.getMid());
	     
	      List<FpMemVO> list = fpMemService.idCheck(fvo);
	      logger.info("idCheck list.size() >>> :" + list.size());
	     
	      String msg = "";
	      if(list.size() == 0) {msg = "ID_YES";}
	      else { msg = "ID_NO";}
	     
	      return msg;
	   }
	  
	   //검색 기능
	   @GetMapping(value = "moodSelectMain")
	   public String moodSelectMain(@ModelAttribute FpMemVO fvo, Model model) {
	       logger.info(" getSearchvalue  >>> : " + fvo.getSearchvalue());
	      
	       List<FpMemVO> moodSelectMain = fpMemService.moodSelectMain(fvo);
	       int nCnt = moodSelectMain.size();
	      
	       String searchvalue = fvo.getSearchvalue();
	      
	       logger.info(" moodSelectMain nCnt >>> : " + nCnt);
	       logger.info(" moodSelectMain >>> : " + searchvalue);
	       if (nCnt > 0 && searchvalue != null) {
	           logger.info(" moodSelectMain >>> : " + moodSelectMain);
	           model.addAttribute("searchList", moodSelectMain);
	           // 검색 조건에 따라 동적으로 페이지 결정
	           if ("MODERN".equals(searchvalue.toUpperCase())) {
	        	   logger.info(" searchvalue >>> : " + searchvalue.toUpperCase());
	               return "search/modern";
	           } else if ("UNIQUE".equals(searchvalue.toUpperCase())) {
	               return "search/unique";
	           } else if ("VINTAGE".equals(searchvalue.toUpperCase())) {
	               return "search/vintage";
	           } else if ("FRENCH_PROVANCE".equals(searchvalue.toUpperCase())) {
	               return "search/french_provance";
	           } else if ("LOVELY_ROMANTIC".equals(searchvalue.toUpperCase())) {
	               return "search/lovely_romantic";
	           } else if ("INDUSTRIAL".equals(searchvalue.toUpperCase())) {
	               return "search/INDUSTRIAL";
	           }
	     	          
	       }
	       return "main/fail";
	   }
	
}