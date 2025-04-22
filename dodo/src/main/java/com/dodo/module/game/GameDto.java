package com.dodo.module.game;

import org.springframework.web.multipart.MultipartFile;

import com.dodo.module.file.FileDto;

public class GameDto extends FileDto {
	
	private String gSeq;
	private String gName;
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
	private String gGDName;
	private String gGAAName;
	private String gGPName;
	private String gRegiDate;
	private String gUpdtDate;
	
	private double rScoreAvg; 	// 리뷰 테이블에서 점수 평균 가져온 값
	private int rCount; 		// 리뷰 갯수
	private int rOrder; 		// 리뷰 테이블 점수 평균으로 매긴 순위
	
	// Image File uploaded
	private String gtfSeq;
	private int gtfTypeCd;
	private String gtfPath;
	private String gtfOriginalName;
	private String gtfUuidName;
	private String gtfExt;
	private long gtfSize;
	private MultipartFile gtfUploadImg;
	
	// 평점 별 표시를 위한 값
	private int fillStarCount; 	// 다 채운 별
	private int harfStarCount; 	// 반 채운 별
	private int emptyStarCount; // 빈 별
	
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
		
		// 별표 셋팅
		double scoreAvg = rScoreAvg / 2.0;
		this.fillStarCount = (int) scoreAvg;
		this.harfStarCount = (scoreAvg - this.fillStarCount) > 0 ? 1 : 0;
		this.emptyStarCount = 5 - this.fillStarCount - this.harfStarCount;
	}

	public int getgCategoryCd() {
		return gCategoryCd;
	}

	public void setgCategoryCd(int gCategoryCd) {
		this.gCategoryCd = gCategoryCd;
	}

	public String getgGDName() {
		return gGDName;
	}

	public void setgGDName(String gGDName) {
		this.gGDName = gGDName;
	}

	public String getgGAAName() {
		return gGAAName;
	}

	public void setgGAAName(String gGAAName) {
		this.gGAAName = gGAAName;
	}

	public String getgGPName() {
		return gGPName;
	}

	public void setgGPName(String gGPName) {
		this.gGPName = gGPName;
	}

	public int getrOrder() {
		return rOrder;
	}

	public void setrOrder(int rOrder) {
		this.rOrder = rOrder;
	}

	public int getrCount() {
		return rCount;
	}

	public void setrCount(int rCount) {
		this.rCount = rCount;
	}

	public String getGtfSeq() {
		return gtfSeq;
	}

	public void setGtfSeq(String gtfSeq) {
		this.gtfSeq = gtfSeq;
	}

	public int getGtfTypeCd() {
		return gtfTypeCd;
	}

	public void setGtfTypeCd(int gtfTypeCd) {
		this.gtfTypeCd = gtfTypeCd;
	}

	public String getGtfPath() {
		return gtfPath;
	}

	public void setGtfPath(String gtfPath) {
		this.gtfPath = gtfPath;
	}

	public String getGtfOriginalName() {
		return gtfOriginalName;
	}

	public void setGtfOriginalName(String gtfOriginalName) {
		this.gtfOriginalName = gtfOriginalName;
	}

	public String getGtfUuidName() {
		return gtfUuidName;
	}

	public void setGtfUuidName(String gtfUuidName) {
		this.gtfUuidName = gtfUuidName;
	}

	public String getGtfExt() {
		return gtfExt;
	}

	public void setGtfExt(String gtfExt) {
		this.gtfExt = gtfExt;
	}

	public long getGtfSize() {
		return gtfSize;
	}

	public void setGtfSize(long gtfSize) {
		this.gtfSize = gtfSize;
	}

	public MultipartFile getGtfUploadImg() {
		return gtfUploadImg;
	}

	public void setGtfUploadImg(MultipartFile gtfUploadImg) {
		this.gtfUploadImg = gtfUploadImg;
	}

	public int getFillStarCount() {
		return fillStarCount;
	}

	public void setFillStarCount(int fillStarCount) {
		this.fillStarCount = fillStarCount;
	}

	public int getHarfStarCount() {
		return harfStarCount;
	}

	public void setHarfStarCount(int harfStarCount) {
		this.harfStarCount = harfStarCount;
	}

	public int getEmptyStarCount() {
		return emptyStarCount;
	}

	public void setEmptyStarCount(int emptyStarCount) {
		this.emptyStarCount = emptyStarCount;
	}

}
