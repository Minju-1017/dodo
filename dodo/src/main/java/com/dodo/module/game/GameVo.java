package com.dodo.module.game;

import com.dodo.module.BaseVo;

public class GameVo extends BaseVo {
	
	private String gSeq;
	
	private Integer shMinLevel;		// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shMaxLevel;		// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shMinScoreAvg;	// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shMaxScoreAvg;	// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shMinPeople;	// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shMaxPeople; 	// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shUseAge; 		// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shMinTime; 		// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shMaxTime; 		// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용
	private Integer shOfficial; 	// null 값을 받아야 되는 경우가 있어서 int 대신 Integer 사용

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

	public Integer getShMinPeople() {
		return shMinPeople;
	}

	public void setShMinPeople(Integer shMinPeople) {
		this.shMinPeople = shMinPeople;
	}

	public Integer getShMaxPeople() {
		return shMaxPeople;
	}

	public void setShMaxPeople(Integer shMaxPeople) {
		this.shMaxPeople = shMaxPeople;
	}

	public Integer getShUseAge() {
		return shUseAge;
	}

	public void setShUseAge(Integer shUseAge) {
		this.shUseAge = shUseAge;
	}

	public Integer getShMinTime() {
		return shMinTime;
	}

	public void setShMinTime(Integer shMinTime) {
		this.shMinTime = shMinTime;
	}

	public Integer getShMaxTime() {
		return shMaxTime;
	}

	public void setShMaxTime(Integer shMaxTime) {
		this.shMaxTime = shMaxTime;
	}

	public Integer getShOfficial() {
		return shOfficial;
	}

	public void setShOfficial(Integer shOfficial) {
		this.shOfficial = shOfficial;
	}

}
