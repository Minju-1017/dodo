package com.dodo.module.game;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dodo.module.Constants;

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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gRegiDate;
	private String gRegiDateStr;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gUpdtDate;
	private String gUpdtDateStr;
	
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
	
	public Date getgRegiDate() {
		return gRegiDate;
	}
	
	public void setgRegiDate(Date gRegiDate) {
		this.gRegiDate = gRegiDate;
		this.gRegiDateStr = Constants.DATETIME_FORMAT.format(gRegiDate);
	}
	
	public String getgRegiDateStr() {
		return gRegiDateStr;
	}
	
	public Date getgUpdtDate() {
		return gUpdtDate;
	}
	
	public void setgUpdtDate(Date gUpdtDate) {
		this.gUpdtDate = gUpdtDate;
		this.gUpdtDateStr = Constants.DATETIME_FORMAT.format(gUpdtDate);
	}
	
	public String getgUpdtDateStr() {
		return gUpdtDateStr;
	}
	
}
