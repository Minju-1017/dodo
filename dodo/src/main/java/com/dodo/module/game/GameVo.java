package com.dodo.module.game;

import java.util.LinkedHashMap;
import java.util.Map;

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
	
	private Integer shOrderOption = 1; 	// 1: 평점 높은순, 2: 평점 낮은순, 3: 난이도 낮은순, 4: 난이도 높은순, 
										// 5: 소요시간 짧은순, 6: 소요시간 긴순, 7: 인원 적은순, 8: 인원 많은순
	
	// 인원 체크박스
	private Map<String, Boolean> shPeopleList = new LinkedHashMap<String, Boolean>();
	
	private Boolean shPeople1p = false;
	private Boolean shPeople2p = false;
	private Boolean shPeople3p = false;
	private Boolean shPeople4p = false;
	private Boolean shPeople5p = false;
	private Boolean shPeople6p = false;
	private Boolean shPeople7p = false;
	
	// 시간 체크박스
	private Boolean shTime10 = false;
	private Boolean shTime20 = false;
	private Boolean shTime30 = false;
	private Boolean shTime60 = false;
	private Boolean shTime90 = false;
	private Boolean shTime90Plus = false;
	
	// 레벨 체크박스
	private Boolean shLevel5 = false;
	private Boolean shLevel4 = false;
	private Boolean shLevel3 = false;
	private Boolean shLevel2 = false;
	private Boolean shLevel1 = false;
	
	// 점수 체크박스
	private Boolean shScoreAvg10;
	private Boolean shScoreAvg8;
	private Boolean shScoreAvg6;
	private Boolean shScoreAvg4;
	private Boolean shScoreAvg2;
	
	public GameVo() {
		for (int i = 1; i < 7; i++) {
			shPeopleList.put(i + "인", false);
		}
		shPeopleList.put("7인 이상", false);
	}

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

	public Integer getShOrderOption() {
		return shOrderOption;
	}

	public void setShOrderOption(Integer shOrderOption) {
		this.shOrderOption = shOrderOption;
	}

	public Boolean getShPeople1p() {
		return shPeople1p;
	}

	public void setShPeople1p(Boolean shPeople1p) {
		this.shPeople1p = shPeople1p;
	}

	public Boolean getShPeople2p() {
		return shPeople2p;
	}

	public void setShPeople2p(Boolean shPeople2p) {
		this.shPeople2p = shPeople2p;
	}

	public Boolean getShPeople3p() {
		return shPeople3p;
	}

	public void setShPeople3p(Boolean shPeople3p) {
		this.shPeople3p = shPeople3p;
	}

	public Boolean getShPeople4p() {
		return shPeople4p;
	}

	public void setShPeople4p(Boolean shPeople4p) {
		this.shPeople4p = shPeople4p;
	}

	public Boolean getShPeople5p() {
		return shPeople5p;
	}

	public void setShPeople5p(Boolean shPeople5p) {
		this.shPeople5p = shPeople5p;
	}

	public Boolean getShPeople6p() {
		return shPeople6p;
	}

	public void setShPeople6p(Boolean shPeople6p) {
		this.shPeople6p = shPeople6p;
	}

	public Boolean getShPeople7p() {
		return shPeople7p;
	}

	public void setShPeople7p(Boolean shPeople7p) {
		this.shPeople7p = shPeople7p;
	}

	public Boolean getShTime10() {
		return shTime10;
	}

	public void setShTime10(Boolean shTime10) {
		this.shTime10 = shTime10;
	}

	public Boolean getShTime20() {
		return shTime20;
	}

	public void setShTime20(Boolean shTime20) {
		this.shTime20 = shTime20;
	}

	public Boolean getShTime30() {
		return shTime30;
	}

	public void setShTime30(Boolean shTime30) {
		this.shTime30 = shTime30;
	}

	public Boolean getShTime60() {
		return shTime60;
	}

	public void setShTime60(Boolean shTime60) {
		this.shTime60 = shTime60;
	}

	public Boolean getShTime90() {
		return shTime90;
	}

	public void setShTime90(Boolean shTime90) {
		this.shTime90 = shTime90;
	}

	public Boolean getShTime90Plus() {
		return shTime90Plus;
	}

	public void setShTime90Plus(Boolean shTime90Plus) {
		this.shTime90Plus = shTime90Plus;
	}

	public Boolean getShLevel5() {
		return shLevel5;
	}

	public void setShLevel5(Boolean shLevel5) {
		this.shLevel5 = shLevel5;
	}

	public Boolean getShLevel4() {
		return shLevel4;
	}

	public void setShLevel4(Boolean shLevel4) {
		this.shLevel4 = shLevel4;
	}

	public Boolean getShLevel3() {
		return shLevel3;
	}

	public void setShLevel3(Boolean shLevel3) {
		this.shLevel3 = shLevel3;
	}

	public Boolean getShLevel2() {
		return shLevel2;
	}

	public void setShLevel2(Boolean shLevel2) {
		this.shLevel2 = shLevel2;
	}

	public Boolean getShLevel1() {
		return shLevel1;
	}

	public void setShLevel1(Boolean shLevel1) {
		this.shLevel1 = shLevel1;
	}

	public Boolean getShScoreAvg10() {
		return shScoreAvg10;
	}

	public void setShScoreAvg10(Boolean shScoreAvg10) {
		this.shScoreAvg10 = shScoreAvg10;
	}

	public Boolean getShScoreAvg8() {
		return shScoreAvg8;
	}

	public void setShScoreAvg8(Boolean shScoreAvg8) {
		this.shScoreAvg8 = shScoreAvg8;
	}

	public Boolean getShScoreAvg6() {
		return shScoreAvg6;
	}

	public void setShScoreAvg6(Boolean shScoreAvg6) {
		this.shScoreAvg6 = shScoreAvg6;
	}

	public Boolean getShScoreAvg4() {
		return shScoreAvg4;
	}

	public void setShScoreAvg4(Boolean shScoreAvg4) {
		this.shScoreAvg4 = shScoreAvg4;
	}

	public Boolean getShScoreAvg2() {
		return shScoreAvg2;
	}

	public void setShScoreAvg2(Boolean shScoreAvg2) {
		this.shScoreAvg2 = shScoreAvg2;
	}

	public Map<String, Boolean> getShPeopleList() {
		return shPeopleList;
	}

	public void setShPeopleList(Map<String, Boolean> shPeopleList) {
		this.shPeopleList = shPeopleList;
	}
	
}
