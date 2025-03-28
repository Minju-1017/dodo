package com.dodo.module.code;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.dodo.Constants;

public class CodeDto {
	
	public static List<CodeDto> cachedCodeArrayList = new ArrayList<CodeDto>(); // 전체 코드 리스트
	
	private String cSeq;
	private String cName;
	private String cNameEng;
	private int cSequence;
	private String cDescription;
	private int cUseNy;
	private String cUseNyStr;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cRegiDate;
	private String cRegiDateStr;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cUpdtDate;
	private String cUpdtDateStr;
	
	private String codeGroup_cgSeq;
	private String cgName;
	
	public static List<CodeDto> getCachedCodeArrayList() {
		return cachedCodeArrayList;
	}

	public static void setCachedCodeArrayList(List<CodeDto> cachedCodeArrayList) {
		CodeDto.cachedCodeArrayList = cachedCodeArrayList;
	}

	public String getcSeq() {
		return cSeq;
	}

	public void setcSeq(String cSeq) {
		this.cSeq = cSeq;
	}

	public String getcName() {
		return cName;
	}
	
	public void setcName(String cName) {
		this.cName = cName;
	}
	
	public String getcNameEng() {
		return cNameEng;
	}
	
	public void setcNameEng(String cNameEng) {
		this.cNameEng = cNameEng;
	}
	
	public int getcSequence() {
		return cSequence;
	}
	
	public void setcSequence(int cSequence) {
		this.cSequence = cSequence;
	}
	
	public String getcDescription() {
		return cDescription;
	}
	
	public void setcDescription(String cDescription) {
		this.cDescription = cDescription;
	}
	
	public int getcUseNy() {
		return cUseNy;
	}
	
	public void setcUseNy(int cUseNy) {
		this.cUseNy = cUseNy;
		this.cUseNyStr = (cUseNy == 1) ? "Y" : "N";
	}
	
	public String getcUseNyStr() {
		return cUseNyStr;
	}

	public Date getcRegiDate() {
		return cRegiDate;
	}

	public void setcRegiDate(Date cRegiDate) {
		this.cRegiDate = cRegiDate;
		this.cRegiDateStr = Constants.DATETIME_FORMAT.format(cRegiDate);
	}

	public String getcRegiDateStr() {
		return cRegiDateStr;
	}

	public Date getcUpdtDate() {
		return cUpdtDate;
	}
	
	public void setcUpdtDate(Date cUpdtDate) {
		this.cUpdtDate = cUpdtDate;
		this.cUpdtDateStr = Constants.DATETIME_FORMAT.format(cUpdtDate);
	}
	
	public String getcUpdtDateStr() {
		return cUpdtDateStr;
	}

	public String getCodeGroup_cgSeq() {
		return codeGroup_cgSeq;
	}

	public void setCodeGroup_cgSeq(String codeGroup_cgSeq) {
		this.codeGroup_cgSeq = codeGroup_cgSeq;
	}

	public String getCgName() {
		return cgName;
	}

	public void setCgName(String cgName) {
		this.cgName = cgName;
	}
	
}
