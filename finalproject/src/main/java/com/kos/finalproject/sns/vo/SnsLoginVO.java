package com.kos.finalproject.sns.vo;


public class SnsLoginVO {
	
	private String mid;
	private String mpw;	
	private String mhp;
	private String memail;	
	private String mphoto;
	
	
	// SNS 로그인 
	private String snstype; // 01:카카오, 02:네이버
	private String snsid;
	private String snsemail;
	
	// 생성자 
	public SnsLoginVO() {
		
	}

	
	public SnsLoginVO(String mid, String mpw, String mhp, String memail, String mphoto, String snstype, String snsid,
			String snsemail) {
	
		this.mid = mid;
		this.mpw = mpw;
		this.mhp = mhp;
		this.memail = memail;
		this.mphoto = mphoto;
		this.snstype = snstype;
		this.snsid = snsid;
		this.snsemail = snsemail;
	}

	// getter / setter 
	public String getMid() {
		return mid;
	}
	public String getMpw() {
		return mpw;
	}
	public String getMhp() {
		return mhp;
	}
	public String getMemail() {
		return memail;
	}
	public String getMphoto() {
		return mphoto;
	}
	public String getSnstype() {
		return snstype;
	}
	public String getSnsid() {
		return snsid;
	}
	public String getSnsemail() {
		return snsemail;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public void setMhp(String mhp) {
		this.mhp = mhp;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public void setMphoto(String mphoto) {
		this.mphoto = mphoto;
	}
	public void setSnstype(String snstype) {
		this.snstype = snstype;
	}
	public void setSnsid(String snsid) {
		this.snsid = snsid;
	}
	public void setSnsemail(String snsemail) {
		this.snsemail = snsemail;
	}
	
	
	
}
