package com.kos.finalproject.fp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.kos.finalproject.fp.vo.FpMemVO;
import com.kos.finalproject.fp.service.FpMemService;

@Controller
public class FpMemController{
	Logger logger = LogManager.getLogger(FpMemController.class);
	   //서비스 연결
	   @Autowired
	   private FpMemService fpMemService;
	
	
	//로그인폼
	   @RequestMapping(value= "fpLoginForm", method = RequestMethod.GET)
	   //@GetMapping("fpLoginForm")
	   public String loginForm() {
	      logger.info("FpLoginController loginForm() 함수 진입 >>> : ");
	      return"main/fpLoginForm";
	   }
	   
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
	         
	         
	         return "main/loginSuccess";
	      }   
	      return "main/fpLoginForm";
	   }
	   
	   
	// 로그 아웃   
	   @RequestMapping(value="logout", method=RequestMethod.GET)
	   public String logout() {   
	      logger.info("OsLoginController logout() 함수 진입 >>> : ");      
	         
	      return "main/fpLoginForm";
	   }
	   
	   
	// 업데이트 폼
	   @RequestMapping(value="fpUpdateForm", method=RequestMethod.GET)
	   public String loginform() {   
	      logger.info("OsLoginController fpUpdateForm() 함수 진입 >>> : ");      
	         
	      return "main/fpUpdateForm";
	   }
	   
	   
	//회원정보 수정
	   @RequestMapping(value="memUpdate", method=RequestMethod.GET)
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
	   @RequestMapping(value = "memDelete", method=RequestMethod.GET)
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
	   @RequestMapping(value= "fpInsertForm", method = RequestMethod.GET)
	   //@GetMapping("fpLoginForm")
	   public String fpInsertForm() {
	      logger.info("FpLoginController fpInsertForm() 함수 진입 >>> : ");
	      return"main/fpInsertForm";
	   } 
	   
	   
	 //회원가입
	   @RequestMapping(value="fpMemInsert", method=RequestMethod.GET)
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
	   @RequestMapping(value="idCheck", method=RequestMethod.POST)
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
	   
	
}