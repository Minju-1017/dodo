package com.dodo.module.member;

import com.dodo.module.BaseVo;

public class MemberVo extends BaseVo {
	
	private int mSeq;
	
	// Search
	private Integer shGender; // null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용

	public int getmSeq() {
		return mSeq;
	}

	public void setmSeq(int mSeq) {
		this.mSeq = mSeq;
	}

	public Integer getShGender() {
		return shGender;
	}

	public void setShGender(Integer shGender) {
		this.shGender = shGender;
	}

}
