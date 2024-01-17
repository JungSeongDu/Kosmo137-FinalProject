package com.kos.finalproject.fp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kos.finalproject.fp.vo.FpMemVO;

@Repository
public class FpMemDAOImpl implements FpMemDAO {
	Logger logger = LogManager.getLogger(FpMemDAOImpl.class);
	
	@Autowired(required=false)
	@Resource(name = "sqlSession_oracle")
	private DefaultSqlSessionFactory sqlSession;
	
	//로그인
	@Override
	public List<FpMemVO> loginCheck(FpMemVO fvo){
		// 로그를 통해 쿼리 파라미터 출력
        logger.info("Login Check - Parameter: mid : " + fvo.getMid());
        logger.info("Login Check - Parameter: mpw : " + fvo.getMpw());
		return sqlSession.openSession().selectList("loginCheck", fvo);
	}
	
	//회원 정보 수정
	@Override
	public int memUpdate(FpMemVO fvo) {
		logger.info("memUpdate 함수 진입 >>> : ");	
		
		return sqlSession.openSession().update("memUpdate", fvo);
	}
	
	//회원 탈퇴
	@Override
	public int memDelete(FpMemVO fvo) {
		logger.info("osBoardDelete 함수 진입 >>> : ");	
		
		return sqlSession.openSession().update("memDelete", fvo);
	}
	
	//아이디 체크
	@Override
	public List<FpMemVO> idCheck(FpMemVO ovo) {
		// TODO Auto-generated method stub
		return sqlSession.openSession().selectList("idCheck", ovo);
	}
	
	@Override
	public int fpMemInsert(FpMemVO fvo) {
		// TODO Auto-generated method stub
		logger.info("  dao.fpMemInsert >>> : ");
		return (Integer)sqlSession.openSession().insert("fpMemInsert", fvo);
	}
}