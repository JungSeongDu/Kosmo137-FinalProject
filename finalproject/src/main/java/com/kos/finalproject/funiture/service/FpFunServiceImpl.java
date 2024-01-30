package com.kos.finalproject.funiture.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kos.finalproject.funiture.vo.FpFunVO;
import com.kos.finalproject.fp.service.FpMemService;
import com.kos.finalproject.funiture.dao.FpFunDAO;

@Service
@Transactional
public class FpFunServiceImpl implements FpFunService{

Logger logger = LogManager.getLogger(FpFunServiceImpl.class);
	
	@Autowired(required=false)
	private FpFunDAO fpFunDAO;
	
	
		//가구
		@Override
		public List<FpFunVO> funiture(FpFunVO fvo){
			logger.info("FpFunService.FpFunVO() 진입 >>> : ");
			return fpFunDAO.funiture(fvo);
		}
		
		//가구 장바구니 
		@Override
		public int fpInsert(FpFunVO fvo){
			logger.info("FpFunService.FpFunVO() 진입 >>> : ");
			return fpFunDAO.fpInsert(fvo);
		}
		
		
		//장바구니 조회
		@Override
		public List<FpFunVO> kartListAll(FpFunVO fvo){
			logger.info("FpFunService.FpFunVO() 진입 >>> : ");
			return fpFunDAO.kartListAll(fvo);
		}
		
		//장바구니 한거삭제 
		@Override
		public int KartDelete(FpFunVO fvo){
			logger.info("FpFunService.FpFunVO() 진입 >>> : ");
			return fpFunDAO.KartDelete(fvo);
		}
		
		//장바구니 전체,선택 삭제
		@Override
		public int KartDeleteArray(ArrayList<FpFunVO> aList) {
			// TODO Auto-generated method stub
			logger.info("osKartDeleteArray 함수 진입 >>> : ");		
			return fpFunDAO.KartDeleteArray(aList);
		}
		
		
		@Override
		public int fsell(FpFunVO fvo) {
			//TODO Auto-generated method stub
			logger.info("kosBoardInsert 함수 진입 >>> : ");
			return fpFunDAO.fsell(fvo);
			
		}
		
		
		
		//가구수정
		@Override
		public List<FpFunVO> updateOne(FpFunVO fvo){
			logger.info("FpFunService.FpFunVO() 진입 >>> : ");
			return fpFunDAO.updateOne(fvo);
		}
		
		
		
		//가구정보 수정
		@Override
		public int fsellUpdate(FpFunVO fvo){
			logger.info("FpFunService.FpFunVO() 진입 >>> : ");
			return fpFunDAO.fsellUpdate(fvo);
		}
		
		
		//가구정보 삭제
		@Override
		public int fsellDelete(FpFunVO fvo){
			logger.info("FpFunService.FpFunVO() 진입 >>> : ");
			return fpFunDAO.fsellDelete(fvo);
		}
}
