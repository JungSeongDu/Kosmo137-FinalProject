package com.kos.finalproject.funiture.service;


import java.util.ArrayList;
import java.util.List;
import com.kos.finalproject.funiture.vo.FpFunVO;

public interface FpFunService {
	
	public List<FpFunVO> funiture(FpFunVO fvo); //가구
	public int fpInsert(FpFunVO fvo); //가구 장바구니 
	
	public List<FpFunVO> kartListAll(FpFunVO fvo); //장바구니 조회
	
	public int KartDelete(FpFunVO fvo); //장바구니 한건 삭제 
	
	public int KartDeleteArray(ArrayList<FpFunVO> aList); //장바구니 전체,선택 삭제
}
