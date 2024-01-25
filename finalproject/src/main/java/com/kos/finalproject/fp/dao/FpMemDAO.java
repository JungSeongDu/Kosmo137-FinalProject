package com.kos.finalproject.fp.dao;


import java.util.List;

import com.kos.finalproject.fp.vo.FpMemVO;

public interface FpMemDAO {

	public List<FpMemVO> loginCheck(FpMemVO fvo); 		//로그인
	public int memUpdate(FpMemVO fvo);					//회원정보 수정
	public int memDelete(FpMemVO fvo);					//회원 탈퇴
	public List<FpMemVO> idCheck(FpMemVO fvo);				//아이디 체크
	public int fpMemInsert(FpMemVO fvo);					//회원가입
	
	public List<FpMemVO> moodSelectMain(FpMemVO fvo);		//검색
}
