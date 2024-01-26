package com.kos.finalproject.funiture.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kos.finalproject.common.CommonUtils;
import com.kos.finalproject.fp.controller.FpMemController;
import com.kos.finalproject.fp.service.FpMemService;
import com.kos.finalproject.funiture.service.FpFunService;
import com.kos.finalproject.funiture.vo.FpFunVO;

@Controller
public class FpFunController {

		Logger logger = LogManager.getLogger(FpMemController.class);
		   //서비스 연결
		   @Autowired
		   private FpFunService fpFunService;
	
	
   //로그인폼
   @RequestMapping("fpLoginForm")
   //@GetMapping("fpLoginForm")
   public String loginForm() {
      logger.info("FpFunController loginForm() 함수 진입 >>> : ");
      return"main/fpLoginForm";
      // http://localhost:8088/finalproject/funiture.h 
   }
   
		   
	
	//가구조회
    @GetMapping("funiture")
    public String osLectureSelectM(FpFunVO fvo, Model model){
       logger.info("funiture 컨트롤러 진입>>> : ");
       logger.info("Received mid: ");
       
       int pageSize = CommonUtils.FUNITURE_PAGE_SIZE;
       int groupSize = CommonUtils.FUNITURE_GROUP_SIZE;
       int curPage = CommonUtils.FUNITURE_CUR_PAGE;
       int totalCount = CommonUtils.FUNITURE_TOTAL_COUNT;
       
       if (fvo.getCurPage() !=null){
          curPage = Integer.parseInt(fvo.getCurPage());
       }
       
       fvo.setPageSize(String.valueOf(pageSize));
       fvo.setGroupSize(String.valueOf(groupSize));
       fvo.setCurPage(String.valueOf(curPage));
       fvo.setTotalCount(String.valueOf(totalCount));
       
       logger.info("getPageSize() >>> : " + fvo.getPageSize());
       logger.info("getGroupSize() >>> : " + fvo.getGroupSize());
       logger.info("getCurPage() >>> : " + fvo.getCurPage());
       logger.info("getTotalCount() >>> : " + fvo.getTotalCount());
       
       
       List<FpFunVO> listAll = fpFunService.funiture(fvo);
       
       logger.info("funiture listAll.size() >>> : " + listAll.size());
       if (listAll.size() > 0) { 
          model.addAttribute("pagingKBVO", fvo);      
          model.addAttribute("listAll", listAll);
          return "funiture/fpFuniture";
          
       } return "main/fail";
    }
	
    
    //가구 장바구니 
    @RequestMapping("fpInsert")
    public String fpInsert(FpFunVO fvo, Model model){
       logger.info("fpInsert 컨트롤러 진입>>> : ");
       logger.info("fpInsert 컨트롤러 진입>>> : " + fvo.getFnum());
       int nCnt = fpFunService.fpInsert(fvo);
       
              if (nCnt > 0) { 
            	  logger.info("osKartInsert 함수 진입 nCnt >>> : " + nCnt);
            	  logger.info("잘 담겼음");
            	  
          return "funiture/returnFuniture";
          
    } return "funiture/funitureKart";
 }
    
  //카트 목록
  	@GetMapping(value="kartSelectAll")
  	public String kartSelectAll(FpFunVO fvo, Model model) {
  		logger.info("kartSelectAll 함수 진입 >>> : ");
  		
  		//okvo.setKnum("1234");
  		//logger.info("okv0.getKnum() >>> : ");
  		
  		List<FpFunVO> kartListAll = fpFunService.kartListAll(fvo);
  		if(kartListAll.size()>0) {
  			logger.info("OsKartController listAll.size() >>> : " + kartListAll.size());
  			model.addAttribute("kartListAll",kartListAll);
  			return "funiture/funitureKart";
  		}
  		return "funiture/reFunitureKart";
  	}
  	
  	//장바구니 한건 삭제
  	@GetMapping(value="KartDelete")
  	public String osKartDelete(HttpServletRequest req, FpFunVO fvo, Model model) {
  		
  		logger.info("osKartDelete 함수 진입 >>> : ");
  		
  		fvo.setFnum(req.getParameter("fnumV"));
  		logger.info("KArtDelete 함수 진입 okvo.getFnum() >>> : " + fvo.getFnum());
  		
  		int nCnt = fpFunService.KartDelete(fvo);
  		if(nCnt > 0) {
  			logger.info("KartDelete 함수 진입 nCnt >>> : " + nCnt);
  		}
  		return "funiture/KartDelete";
  	}
  	
  	
  //선택삭제 전체 삭제
  	@GetMapping(value="KartDeleteArray")
  	public String KartDeleteArray(HttpServletRequest req,FpFunVO fvo, Model model) {
  		logger.info("KartDeleteArray 함수 진입 >>> : ");
  		
  		String fnumV[] = req.getParameterValues("knum");
  		ArrayList<FpFunVO> aList = new ArrayList<FpFunVO>();
  		
  		for(int i=0; i<fnumV.length; i++) {
  			FpFunVO _fvo = new FpFunVO();
  			_fvo.setFnum(fnumV[i]);
  			aList.add(_fvo);
  		}
  		
  		int nCnt = fpFunService.KartDeleteArray(aList);		
  		if (nCnt == -1) {
  			logger.info("osKartDeleteArray 함수 진입 nCnt >>> : " + nCnt);
  		}
  		return "funiture/KartDelete";
  	}
  	
    
}
