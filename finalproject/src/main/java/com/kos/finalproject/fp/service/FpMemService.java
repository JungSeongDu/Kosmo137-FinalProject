package com.kos.finalproject.fp.service;

import java.util.List;
import com.kos.finalproject.fp.vo.FpMemVO;

public interface FpMemService {
	public List<FpMemVO> loginCheck(FpMemVO fvo);			//로그인
	public int memUpdate(FpMemVO fvo);						//회원정보 수정
	public int memDelete(FpMemVO fvo);						//회원 탈퇴
	public List<FpMemVO> idCheck(FpMemVO fvo);				//아이디 체크
	public int fpMemInsert(FpMemVO fvo);					//회원가입
}
