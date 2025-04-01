package com.dodo.module.game;

public class GameDto {
	
	private String gSeq;
	private String gName;
	private String gTnFileName;
	private double gLevel;
	private int gMinPeople;
	private int gMaxPeople;
	private int gBestPeople;
	private int gRecommendPeople;
	private int gUseAge;
	private int gMinTime;
	private int gMaxTime;
	private int gOfficialCd;
	private String gOfficialUrl;
	private String gComuUrl1;
	private String gComuUrl2;
	private int gCategoryCd;
	private String gRegiDate;
	private String gUpdtDate;
	private double rScoreAvg; // 리뷰 테이블에서 점수 평균 가져온 값
	
	public String getgSeq() {
		return gSeq;
	}
	
	public void setgSeq(String gSeq) {
		this.gSeq = gSeq;
	}
	
	public String getgName() {
		return gName;
	}
	
	public void setgName(String gName) {
		this.gName = gName;
	}
	
	public String getgTnFileName() {
		return gTnFileName;
	}
	
	public void setgTnFileName(String gTnFileName) {
		this.gTnFileName = gTnFileName;
	}
	
	public double getgLevel() {
		return gLevel;
	}
	
	public void setgLevel(double gLevel) {
		this.gLevel = gLevel;
	}
	
	public int getgMinPeople() {
		return gMinPeople;
	}
	
	public void setgMinPeople(int gMinPeople) {
		this.gMinPeople = gMinPeople;
	}
	
	public int getgMaxPeople() {
		return gMaxPeople;
	}
	
	public void setgMaxPeople(int gMaxPeople) {
		this.gMaxPeople = gMaxPeople;
	}
	
	public int getgBestPeople() {
		return gBestPeople;
	}
	
	public void setgBestPeople(int gBestPeople) {
		this.gBestPeople = gBestPeople;
	}
	
	public int getgRecommendPeople() {
		return gRecommendPeople;
	}
	
	public void setgRecommendPeople(int gRecommendPeople) {
		this.gRecommendPeople = gRecommendPeople;
	}
	
	public int getgUseAge() {
		return gUseAge;
	}
	
	public void setgUseAge(int gUseAge) {
		this.gUseAge = gUseAge;
	}
	
	public int getgMinTime() {
		return gMinTime;
	}
	
	public void setgMinTime(int gMinTime) {
		this.gMinTime = gMinTime;
	}
	
	public int getgMaxTime() {
		return gMaxTime;
	}
	
	public void setgMaxTime(int gMaxTime) {
		this.gMaxTime = gMaxTime;
	}
	
	public int getgOfficialCd() {
		return gOfficialCd;
	}
	
	public void setgOfficialCd(int gOfficialCd) {
		this.gOfficialCd = gOfficialCd;
	}
	
	public String getgOfficialUrl() {
		return gOfficialUrl;
	}
	
	public void setgOfficialUrl(String gOfficialUrl) {
		this.gOfficialUrl = gOfficialUrl;
	}
	
	public String getgComuUrl1() {
		return gComuUrl1;
	}
	
	public void setgComuUrl1(String gComuUrl1) {
		this.gComuUrl1 = gComuUrl1;
	}
	
	public String getgComuUrl2() {
		return gComuUrl2;
	}
	
	public void setgComuUrl2(String gComuUrl2) {
		this.gComuUrl2 = gComuUrl2;
	}
	
	public String getgRegiDate() {
		return gRegiDate;
	}
	
	public void setgRegiDate(String gRegiDate) {
		this.gRegiDate = gRegiDate;
	}
	
	public String getgUpdtDate() {
		return gUpdtDate;
	}
	
	public void setgUpdtDate(String gUpdtDate) {
		this.gUpdtDate = gUpdtDate;
	}

	public double getrScoreAvg() {
		return rScoreAvg;
	}

	public void setrScoreAvg(double rScoreAvg) {
		this.rScoreAvg = rScoreAvg;
	}

	public int getgCategoryCd() {
		return gCategoryCd;
	}

	public void setgCategoryCd(int gCategoryCd) {
		this.gCategoryCd = gCategoryCd;
	}
	
}
