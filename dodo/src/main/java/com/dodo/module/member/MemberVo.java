package com.dodo.module.member;

import com.dodo.module.BaseVo;

public class MemberVo extends BaseVo {
	
	private String mSeq;
	
	// Search
	private Integer shGenderCd; // null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shGradeCd; 	// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용

	public String getmSeq() {
		return mSeq;
	}

	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
	}

	public Integer getShGenderCd() {
		return shGenderCd;
	}

	public void setShGenderCd(Integer shGenderCd) {
		this.shGenderCd = shGenderCd;
	}

	public Integer getShGradeCd() {
		return shGradeCd;
	}

	public void setShGradeCd(Integer shGradeCd) {
		this.shGradeCd = shGradeCd;
	}

}
