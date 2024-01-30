package com.kos.finalproject.funiture.dao;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.kos.finalproject.funiture.vo.FpFunVO;


@Repository
public class FpFunDAOImpl implements FpFunDAO {
	
Logger logger = LogManager.getLogger(FpFunDAOImpl.class);
	
	@Autowired(required=false)
	@Resource(name = "sqlSession_oracle")
	private DefaultSqlSessionFactory sqlSession;
	
	//가구
	 @Override
	    public List<FpFunVO> funiture(FpFunVO fvo) {
		 logger.info("FpFunDAOImple" );
	        	        
			return sqlSession.openSession().selectList("funiture", fvo);
	    }
	 
	//가구 장바구니 
	 @Override
	    public int fpInsert(FpFunVO fvo) {
		 logger.info("FpFunDAOImple" );
	        	        
			return sqlSession.openSession().insert("fpInsert", fvo);
	    }
	 
	 
	//장바구니 조회
	 @Override
	    public List<FpFunVO> kartListAll(FpFunVO fvo) {
		 logger.info("FpFunDAOImple" );
	        	        
			return sqlSession.openSession().selectList("kartListAll", fvo);
		    }
	 
	 
	 //장바구니 한건삭제 
	 @Override
	    public int KartDelete(FpFunVO fvo) {
		 logger.info("FpFunDAOImple" );
	        	        
			return sqlSession.openSession().update("KartDelete", fvo);
	    }
	 
	 //장바구니 선택,전체
	 @Override
		public int KartDeleteArray(ArrayList<FpFunVO> aList) {
			// TODO Auto-generated method stub
			logger.info("KartDeleteArray 함수 진입 >>> : ");			
			return sqlSession.openSession().update("KartDeleteArray", aList);
		}
	 
	 //제픔등록
	 @Override
		public int fsell(FpFunVO fvo) {
			//TODO Auto-generated method stub
			logger.info("fsell 함수 진입 >>> : ");
			
			return sqlSession.openSession().insert("fsell",fvo);
			
		}
	 
	 
	 
	//가구 수정 폼
	@Override
	   public List<FpFunVO> updateOne(FpFunVO fvo) {
		 logger.info("FpFunDAOImple" );
	        	        
			return sqlSession.openSession().selectList("updateOne", fvo);
		}
		
	//가구 정보 수정 
	 @Override
	    public int fsellUpdate(FpFunVO fvo) {
		 logger.info("FpFunDAOImple" );
	        	        
			return sqlSession.openSession().update("fsellUpdate", fvo);
	    }
	 
	 
	//가구 정보 수정 
	 @Override
	    public int fsellDelete(FpFunVO fvo) {
		 logger.info("FpFunDAOImple" );
	        	        
			return sqlSession.openSession().update("fsellDelete", fvo);
	    }
}
