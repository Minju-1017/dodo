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
	
	private Map<String, Boolean> shPeopleList = new LinkedHashMap<String, Boolean>(); 	// 인원 체크박스
	private Map<String, Boolean> shTimeList = new LinkedHashMap<String, Boolean>(); 	// 시간 체크박스
	private Map<String, Boolean> shLevelList = new LinkedHashMap<String, Boolean>();	// 레벨 체크박스
	private Map<String, Boolean> shScoreList = new LinkedHashMap<String, Boolean>();	// 평점 체크박스
	
	public GameVo() {
		// 인원 체크박스 초기화
		for (int i = 1; i < 7; i++) {
			shPeopleList.put(i + "인", false);
		}
		shPeopleList.put("7인 이상", false);
		
		// 시간 체크박스 초기화
		shTimeList.put("10분 이내", false);
		shTimeList.put("11 - 20분", false);
		shTimeList.put("21 - 30분", false);
		shTimeList.put("31 - 60분", false);
		shTimeList.put("61 - 90분", false);
		shTimeList.put("90분 초과", false);
		
		// 레벨 체크박스 초기화
		shLevelList.put("(5)", false);
		shLevelList.put("(4 - 4.99)", false);
		shLevelList.put("(3 - 3.99)", false);
		shLevelList.put("(2 - 2.99)", false);
		shLevelList.put("(0 - 1.99)", false);
		
		// 평점 체크박스 초기화
		shScoreList.put("(8 - 10)", false);
		shScoreList.put("(6 - 7.99)", false);
		shScoreList.put("(4 - 5.99)", false);
		shScoreList.put("(2 - 3.99)", false);
		shScoreList.put("(0 - 1.99)", false);
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

	public Map<String, Boolean> getShPeopleList() {
		return shPeopleList;
	}

	public void setShPeopleList(Map<String, Boolean> shPeopleList) {
		this.shPeopleList = shPeopleList;
	}

	public Map<String, Boolean> getShTimeList() {
		return shTimeList;
	}

	public void setShTimeList(Map<String, Boolean> shTimeList) {
		this.shTimeList = shTimeList;
	}

	public Map<String, Boolean> getShLevelList() {
		return shLevelList;
	}

	public void setShLevelList(Map<String, Boolean> shLevelList) {
		this.shLevelList = shLevelList;
	}

	public Map<String, Boolean> getShScoreList() {
		return shScoreList;
	}

	public void setShScoreList(Map<String, Boolean> shScoreList) {
		this.shScoreList = shScoreList;
	}
	
}
