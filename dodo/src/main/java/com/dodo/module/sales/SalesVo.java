package com.dodo.module.sales;

import com.dodo.module.BaseVo;

public class SalesVo extends BaseVo {
	
	private String msSeq;
	private String member_mSeq;
	private String oMember_mSeq;
	
	// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shStateCd;
	private Integer shDeliStateCd;

	public String getMsSeq() {
		return msSeq;
	}

	public void setMsSeq(String msSeq) {
		this.msSeq = msSeq;
	}

	public String getMember_mSeq() {
		return member_mSeq;
	}

	public void setMember_mSeq(String member_mSeq) {
		this.member_mSeq = member_mSeq;
	}

	public String getoMember_mSeq() {
		return oMember_mSeq;
	}

	public void setoMember_mSeq(String oMember_mSeq) {
		this.oMember_mSeq = oMember_mSeq;
	}

	public Integer getShStateCd() {
		return shStateCd;
	}

	public void setShStateCd(Integer shStateCd) {
		this.shStateCd = shStateCd;
	}

	public Integer getShDeliStateCd() {
		return shDeliStateCd;
	}

	public void setShDeliStateCd(Integer shDeliStateCd) {
		this.shDeliStateCd = shDeliStateCd;
	}
	
}
