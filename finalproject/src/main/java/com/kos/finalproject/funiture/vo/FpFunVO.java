package com.kos.finalproject.funiture.vo;

public class FpFunVO {

	
	String fnum;
	String fname;
	String fprice;
	String fmood;
	String deletyn;
	//String lfile;
	
	
	//패이징
	 String pageSize;
	 String groupSize;
	 String curPage;
	 String totalCount;
	 
	 public FpFunVO() {
			
	 }
	 
	 
	 public FpFunVO(String fnum, String fname,String fprice, String fmood,String deleteyn,
			 		String pageSize,String groupSize,String curPage, String totalCount) {
			super();
			
			this.fnum = fnum;
			this.fname = fname;
			this.fprice = fprice;
			this.fmood = fmood;
			this.deletyn = deletyn;
			this.pageSize = pageSize;
			this.groupSize = groupSize;
			this.curPage = curPage;
			this.totalCount = totalCount;
		}


	public String getFnum() {
		return fnum;
	}


	public void setFnum(String fnum) {
		this.fnum = fnum;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getFprice() {
		return fprice;
	}


	public void setFprice(String fprice) {
		this.fprice = fprice;
	}


	public String getFmood() {
		return fmood;
	}


	public void setFmood(String fmood) {
		this.fmood = fmood;
	}


	public String getDeletyn() {
		return deletyn;
	}


	public void setDeletyn(String deletyn) {
		this.deletyn = deletyn;
	}


	public String getPageSize() {
		return pageSize;
	}


	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}


	public String getGroupSize() {
		return groupSize;
	}


	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}


	public String getCurPage() {
		return curPage;
	}


	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}


	public String getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	 
}
