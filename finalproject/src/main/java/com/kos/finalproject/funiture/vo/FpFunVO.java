package com.kos.finalproject.funiture.vo;

public class FpFunVO {

	
	String fnum;
	String fname;
	String fprice;
	String fmood;
	String deletyn;
	
	String fseller;
	String finsertdate;
	String fupdatedate;
	
	
	String ffile;
	
	String knum;
	String kname;
	String kprice;
	String kmood;
	String kdeletyn;
	
	
	//패이징
	 String pageSize;
	 String groupSize;
	 String curPage;
	 String totalCount;
	 
	 public FpFunVO() {
			
	 }
	 
	 
	 public FpFunVO(String fnum, String fname,String fprice, String fmood,String deleteyn,
			 		String fseller, String finsertdate, String fupdatedate,
			 		String pageSize,String groupSize,String curPage, String totalCount, String ffile, 
			 		String knum, String kname, String kprice, String kmood, String kdeletyn) {
			super();
			
			this.fnum = fnum;
			this.fname = fname;
			this.fprice = fprice;
			this.fmood = fmood;
			this.deletyn = deletyn;
			this.ffile = ffile;
			
			this.fseller = fseller;
			this.finsertdate = finsertdate;
			this.fupdatedate = fupdatedate;
			
			
			this.pageSize = pageSize;
			this.groupSize = groupSize;
			this.curPage = curPage;
			this.totalCount = totalCount;
			
			
			
			this.knum = knum;
			this.kname = kname;
			this.kprice = kprice;
			this.kmood = kmood;
			this.kdeletyn = kdeletyn;
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


	public String getFfile() {
		return ffile;
	}


	public void setFfile(String ffile) {
		this.ffile = ffile;
	}


	public String getKnum() {
		return knum;
	}


	public void setKnum(String knum) {
		this.knum = knum;
	}


	public String getKname() {
		return kname;
	}


	public void setKname(String kname) {
		this.kname = kname;
	}


	public String getKprice() {
		return kprice;
	}


	public void setKprice(String kprice) {
		this.kprice = kprice;
	}


	public String getKmood() {
		return kmood;
	}


	public void setKmood(String kmood) {
		this.kmood = kmood;
	}


	public String getKdeletyn() {
		return kdeletyn;
	}


	public void setKdeletyn(String kdeletyn) {
		this.kdeletyn = kdeletyn;
	}


	public String getFseller() {
		return fseller;
	}


	public void setFseller(String fseller) {
		this.fseller = fseller;
	}


	public String getFinsertdate() {
		return finsertdate;
	}


	public void setFinsertdate(String finsertdate) {
		this.finsertdate = finsertdate;
	}


	public String getFupdatedate() {
		return fupdatedate;
	}


	public void setFupdatedate(String fupdatedate) {
		this.fupdatedate = fupdatedate;
	}
	 
}
