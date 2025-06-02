package com.dodo.module.sales;

import com.dodo.module.BaseVo;

public class SalesVo extends BaseVo {
	
	private String msSeq;
	
	private Integer shStateCd; // null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용

	public String getMsSeq() {
		return msSeq;
	}

	public void setMsSeq(String msSeq) {
		this.msSeq = msSeq;
	}

	public Integer getShStateCd() {
		return shStateCd;
	}

	public void setShStateCd(Integer shStateCd) {
		this.shStateCd = shStateCd;
	}
	
}
