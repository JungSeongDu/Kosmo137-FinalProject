package com.kos.finalproject.funiture.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.kos.finalproject.common.CommonUtils;
import com.kos.finalproject.fp.controller.FpMemController;
import com.kos.finalproject.fp.service.FpMemService;
import com.kos.finalproject.fp.vo.FpMemVO;
import com.kos.finalproject.funiture.service.FpFunService;
import com.kos.finalproject.funiture.vo.FpFunVO;

import com.oreilly.servlet.MultipartRequest;   // 구

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
    public String funiture(@RequestParam(required = false) String mid, FpFunVO fvo, Model model){
       logger.info("funiture 컨트롤러 진입>>> : ");
       logger.info("Received mid: " + mid);
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
    	  model.addAttribute("mid", mid); // mid 값을 모델에 추가
          model.addAttribute("pagingKBVO", fvo);
          model.addAttribute("listAll", listAll);
          return "funiture/fpFuniture";
       } return "main/fail";
    }
	
    //가구 장바구니
    @RequestMapping("fpInsert")
    public String fpInsert(@RequestParam(required = false) String mid, FpFunVO fvo, Model model){
       logger.info("fpInsert 컨트롤러 진입>>> : ");
       logger.info("fpInsert 컨트롤러 진입>>> : " + fvo.getFnum());
       logger.info("Received mid: " + mid);
       int nCnt = fpFunService.fpInsert(fvo);
              if (nCnt > 0) {
            	  logger.info("osKartInsert 함수 진입 nCnt >>> : " + nCnt);
            	  logger.info("잘 담겼음");
            	  model.addAttribute("mid", mid); // mid 값을 모델에 추가
            	 
          return "funiture/returnFuniture";
    } return "funiture/funitureKart";
 }
    
  //카트 목록
  	@GetMapping(value="kartSelectAll")
  	public String kartSelectAll(@RequestParam(required = false) String mid, FpFunVO fvo, Model model) {
  		logger.info("kartSelectAll 함수 진입 >>> : ");
  		logger.info("Received mid: " + mid);
  		//okvo.setKnum("1234");
  		//logger.info("okv0.getKnum() >>> : ");
  		
  		List<FpFunVO> kartListAll = fpFunService.kartListAll(fvo);
  		if(kartListAll.size()>0) {
  			logger.info("OsKartController listAll.size() >>> : " + kartListAll.size());
  			model.addAttribute("mid", mid); // mid 값을 모델에 추가
  			model.addAttribute("kartListAll",kartListAll);
  			return "funiture/funitureKart";
  		}
  		model.addAttribute("mid", mid); // mid 값을 모델에 추가
  		return "funiture/reFunitureKart";
  	}
  	
  	//장바구니 한건 삭제
  	@GetMapping(value="KartDelete")
  	public String KartDelete(@RequestParam(required = false) String mid,HttpServletRequest req, FpFunVO fvo, Model model) {
  		
  		logger.info("KartDelete 함수 진입 >>> : ");
  		logger.info("Received mid: " + mid);
  		
  		fvo.setFnum(req.getParameter("fnumV"));
  		logger.info("KArtDelete 함수 진입 okvo.getFnum() >>> : " + fvo.getFnum());
  		
  		int nCnt = fpFunService.KartDelete(fvo);
  		if(nCnt > 0) {
  			logger.info("KartDelete 함수 진입 nCnt >>> : " + nCnt);
  			
  	  	}
  		model.addAttribute("mid", mid); // mid 값을 모델에 추가
  		return "funiture/reKartDelete";
  	}
  	
  	
    //선택삭제 전체 삭제
  	@PostMapping(value="KartDeleteArray")
  	public String KartDeleteArray(@RequestParam(required = false) String mid,HttpServletRequest req,FpFunVO fvo, Model model) {
  		logger.info("KartDeleteArray 함수 진입 >>> : ");
  		logger.info("Received mid: " + mid);
  		
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
  		model.addAttribute("mid", mid); // mid 값을 모델에 추가
  		return "funiture/reKartDelete";
  	}
  	
  	
  //주문
  	@GetMapping(value="Kartorder")
  	public String Kartorder(@RequestParam(required = false) String mid,@RequestParam(required = false) String sum,HttpServletRequest req, FpFunVO fvo, Model model) {
  		
  		logger.info("Kartorder 함수 진입 >>> : ");
  		logger.info("Received mid: " + mid);
  		logger.info("Received sum: " + sum);
  		
  		
  		model.addAttribute("mid", mid); // mid 값을 모델에 추가
  		model.addAttribute("sum", sum); // mid 값을 모델에 추가
  		return "funiture/funitureorder";
  	}
  	
  	
  //등록폼
  	@GetMapping(value="fsellform")
  	public String Kartorder(@RequestParam(required = false) String mid,HttpServletRequest req, FpFunVO fvo, Model model) {
  		
  		logger.info("fsellform 함수 진입 >>> : ");
  		logger.info("Received mid: " + mid);
  		
  		
  		model.addAttribute("mid", mid); // mid 값을 모델에 추가
  		
  		return "funiture/fsellform";
  	}
  	
  	
  //가구등록
  	@PostMapping("fsell")
  	public String fsell(@RequestParam(required = false) String mid,HttpServletRequest req,
  	                    Model model) {
  	    logger.info("fsell 함수 진입 >>> : ");
  	    logger.info("Received mid: " + mid);

  	  // 파일 업로드
        String filePath = CommonUtils.PRODUCT_IMG_UPLOAD_PATH;
        int imgfileSize = CommonUtils.PRODUCT_IMG_FILE_SIZE;
        String encodeType = CommonUtils.PRODUCT_ENCODE;


  	    try {
  	    	
  	    	MultipartRequest mr = new MultipartRequest(req, filePath, imgfileSize, encodeType); 
  	        
  	    	FpFunVO fvo = null;  
  	         fvo = new FpFunVO(); 
  	        
  	         // 입력한 데이터 값 확인
  	         fvo.setFnum(mr.getParameter("fnum"));
  	         fvo.setFname(mr.getParameter("fname"));
  	         fvo.setFprice(mr.getParameter("fprice"));
  	         fvo.setFmood(mr.getParameter("fmood"));
  	         fvo.setFfile(mr.getFilesystemName("ffile"));

  	         
  	        int nCnt = fpFunService.fsell(fvo);

  	        if (nCnt > 0) {
  	            logger.info("kosBoardInsert nCnt >>> : " + nCnt);
  	            model.addAttribute("mid", mid); // mid 값을 모델에 추가
  	            return "funiture/reFunitureKart";
  	        }

  	    } catch (IOException e) {
  	        logger.info("파일 업로드 중 에러가 >>> : " + e.getMessage());
  	    }

  	    return "main/fail";
  	}
  	
  	
  	
  //가구 수정 불러오기
  	@PostMapping(value="updateOne")
  	public String updateOne(@RequestParam(required = false) String mid, FpFunVO fvo, Model model) {
  		logger.info("updateOne 함수 진입 >>> : ");
  		logger.info("Received mid: " + mid);
  		
  		
  		List<FpFunVO> kartListOne = fpFunService.updateOne(fvo);
  		if(kartListOne.size()>0) {
  			logger.info("OsKartController listAll.size() >>> : " + kartListOne.size());
  			model.addAttribute("mid", mid); // mid 값을 모델에 추가
  			model.addAttribute("kartListOne",kartListOne);
  			return "funiture/fsellUpdate";
  		}
  		return "main/fail";
  	}
  	
  	
  //가구정보  수정
	@GetMapping(value="fsellUpdate")
    public String fsellUpdate(@RequestParam(required = false) String mid, FpFunVO fvo, Model model,HttpServletRequest req) {
       logger.info("fsellUpdate >>> :");
       logger.info("fsellUpdate 함수 진입 fvo.getFnum() >>> : " + fvo.getFnum());
       logger.info("fsellUpdate 함수 진입 fvo.getFname() >>> : " + fvo.getFname());
       logger.info("Received mid: " + mid);
 		
       int nCnt = fpFunService.fsellUpdate(fvo);
       
       if (nCnt > 0) {
          logger.info("fpFunService nCnt >>> : " + nCnt);
          model.addAttribute("mid", mid); // mid 값을 모델에 추가
          return "funiture/reFunitureKart";
       }
       return "main/fail";
    }
	
	
	//가구정보  삭제
		@GetMapping(value="fsellDelete")
	    public String fsellDelete(@RequestParam(required = false) String mid, FpFunVO fvo, Model model,HttpServletRequest req) {
	       logger.info("fsellDelete >>> :");
	       logger.info("fsellDelete 함수 진입 fvo.getFnum() >>> : " + fvo.getFnum());
	       
	       int nCnt = fpFunService.fsellDelete(fvo);
	       
	       if (nCnt > 0) {
	          logger.info("fpFunService nCnt >>> : " + nCnt);
	          model.addAttribute("mid", mid); // mid 값을 모델에 추가
	          return "funiture/reFunitureKart";
	       }
	       return "main/fail";
	    }
  	
  	
  	
}