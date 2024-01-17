package com.kos.finalproject.fp.vo;

public class FpMemVO {
		String mnum;
		String mname;
		String mid;
		String mpw;
		String insertdate;
		String updatedate;
		String deleteyn;


public FpMemVO() {
	
}

public FpMemVO(String mnum, String mname, String mid, String mpw, String insertdate, String updatedate, String deleteyn) {
	super();
	
	this.mnum = mnum;
	this.mname = mname;
	this.mid = mid;
	this.mpw = mpw;
	this.insertdate = insertdate;
	this.updatedate = updatedate;
	this.deleteyn = deleteyn;
}

public String getMnum() {
	return mnum;
}

public void setMnum(String mnum) {
	this.mnum = mnum;
}

public String getMname() {
	return mname;
}

public void setMname(String mname) {
	this.mname = mname;
}

public String getMid() {
	return mid;
}

public void setMid(String mid) {
	this.mid = mid;
}

public String getMpw() {
	return mpw;
}

public void setMpw(String mpw) {
	this.mpw = mpw;
}

public String getInsertdate() {
	return insertdate;
}

public void setInsertdate(String insertdate) {
	this.insertdate = insertdate;
}

public String getUpdatedate() {
	return updatedate;
}

public void setUpdatedate(String updatedate) {
	this.updatedate = updatedate;
}

public String getDeleteyn() {
	return deleteyn;
}

public void setDeleteyn(String deleteyn) {
	this.deleteyn = deleteyn;
}


}
