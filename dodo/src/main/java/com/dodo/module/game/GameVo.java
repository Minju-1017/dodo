package com.dodo.module.game;

import com.dodo.module.BaseVo;

public class GameVo extends BaseVo {
	
	private String gSeq;
	
	// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shMinLevel;		
	private Integer shMaxLevel;
	private Integer shMinScoreAvg;
	private Integer shMaxScoreAvg;
	private Integer shPeople;
	private Integer shUseAge;
	private Integer shTime;
	private Integer shOfficialCd;
	private Integer shCategoryCd;

	public String getgSeq() {
		return gSeq;
	}

	public void setgSeq(String gSeq) {
		this.gSeq = gSeq;
	}

	public Integer getShMinLevel() {
		return shMinLevel;
	}

	public void setShMinLevel(Integer shMinLevel) {
		this.shMinLevel = shMinLevel;
	}

	public Integer getShMaxLevel() {
		return shMaxLevel;
	}

	public void setShMaxLevel(Integer shMaxLevel) {
		this.shMaxLevel = shMaxLevel;
	}

	public Integer getShMinScoreAvg() {
		return shMinScoreAvg;
	}

	public void setShMinScoreAvg(Integer shMinScoreAvg) {
		this.shMinScoreAvg = shMinScoreAvg;
	}

	public Integer getShMaxScoreAvg() {
		return shMaxScoreAvg;
	}

	public void setShMaxScoreAvg(Integer shMaxScoreAvg) {
		this.shMaxScoreAvg = shMaxScoreAvg;
	}

	public Integer getShPeople() {
		return shPeople;
	}

	public void setShPeople(Integer shPeople) {
		this.shPeople = shPeople;
	}

	public Integer getShUseAge() {
		return shUseAge;
	}

	public void setShUseAge(Integer shUseAge) {
		this.shUseAge = shUseAge;
	}

	public Integer getShTime() {
		return shTime;
	}

	public void setShTime(Integer shTime) {
		this.shTime = shTime;
	}

	public Integer getShOfficialCd() {
		return shOfficialCd;
	}

	public void setShOfficialCd(Integer shOfficialCd) {
		this.shOfficialCd = shOfficialCd;
	}

	public Integer getShCategoryCd() {
		return shCategoryCd;
	}

	public void setShCategoryCd(Integer shCategoryCd) {
		this.shCategoryCd = shCategoryCd;
	}

}
