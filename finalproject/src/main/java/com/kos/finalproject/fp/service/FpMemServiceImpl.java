package com.kos.finalproject.fp.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kos.finalproject.fp.vo.FpMemVO;
import com.kos.finalproject.fp.dao.FpMemDAO;

@Service
@Transactional
public class FpMemServiceImpl implements FpMemService {
	Logger logger = LogManager.getLogger(FpMemServiceImpl.class);
	
	@Autowired(required=false)
	private FpMemDAO fpMemDAO;
	
	//로그인
	@Override
	public List<FpMemVO> loginCheck(FpMemVO fvo){
		logger.info("FpMemService.idCheck() 진입 >>> : ");
		return fpMemDAO.loginCheck(fvo);
	}
	
	
	//회원 정보 변경
	@Override
	public int memUpdate(FpMemVO fvo){
		logger.info("memUpdate >>> :");
		return fpMemDAO.memUpdate(fvo);
	}
	
	
	//회원정보 삭제
	@Override
	public int memDelete(FpMemVO fvo) {
		logger.info("osMypagedeleteService >>> : ");			
		return fpMemDAO.memDelete(fvo);
	}
	
	//아이디 체크
	@Override
	public List<FpMemVO> idCheck(FpMemVO fvo) {
		// TODO Auto-generated method stub
		logger.info(" FpMemService.idCheck() 진입 >>> : ");
		return fpMemDAO.idCheck(fvo);
	}
	
	//회원가입
	@Override
	public int fpMemInsert(FpMemVO fvo) {
		// TODO Auto-generated method stub
		logger.info("  service.fpMemInsert >>> : ");
		return fpMemDAO.fpMemInsert(fvo);
	}
}
