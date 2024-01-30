package com.kos.finalproject.funiture.dao;

import java.util.ArrayList;
import java.util.List;
import com.kos.finalproject.funiture.vo.FpFunVO;


public interface FpFunDAO {

	public List<FpFunVO> funiture(FpFunVO fvo); //가구
	public int fpInsert(FpFunVO fvo); //가구상세
	public List<FpFunVO> kartListAll(FpFunVO fvo); //장바구니 조회
	public int KartDelete(FpFunVO fvo); //장바구니 한건 삭제 
	public int KartDeleteArray(ArrayList<FpFunVO> aList); //장바구니 전체,선택 삭제
	public int fsell(FpFunVO fvo); //제품등록
	public List<FpFunVO> updateOne(FpFunVO fvo); //가구수정 폼
	public int fsellUpdate(FpFunVO fvo); //가구정보 수정 
	public int fsellDelete(FpFunVO fvo); //가구정보 삭제
}
