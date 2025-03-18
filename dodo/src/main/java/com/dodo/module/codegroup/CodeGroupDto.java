package com.dodo.module.codegroup;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dodo.module.Constants;

public class CodeGroupDto {
	
	private String cgSeq;
	private String cgName;
	private String cgNameEng;
	private int cgSequence;
	private String cgDescription;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cgRegiDate;
	private String cgRegiDateStr;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cgUpdtDate;
	private String cgUpdtDateStr;
	
	private int cgUseNy;
	private String cgUseNyStr;
	private int cCount;

	public String getCgSeq() {
		return cgSeq;
	}

	public void setCgSeq(String cgSeq) {
		this.cgSeq = cgSeq;
	}

	public String getCgName() {
		return cgName;
	}
	
	public void setCgName(String cgName) {
		this.cgName = cgName;
	}
	
	public String getCgNameEng() {
		return cgNameEng;
	}
	
	public void setCgNameEng(String cgNameEng) {
		this.cgNameEng = cgNameEng;
	}
	
	public int getCgSequence() {
		return cgSequence;
	}
	
	public void setCgSequence(int cgSequence) {
		this.cgSequence = cgSequence;
	}
	
	public String getCgDescription() {
		return cgDescription;
	}
	
	public void setCgDescription(String cgDescription) {
		this.cgDescription = cgDescription;
	}
	
	
	public Date getCgRegiDate() {
		return cgRegiDate;
	}

	public void setCgRegiDate(Date cgRegiDate) {
		this.cgRegiDate = cgRegiDate;
		this.cgRegiDateStr = Constants.DATETIME_FORMAT.format(cgRegiDate);
	}

	public String getCgRegiDateStr() {
		return cgRegiDateStr;
	}

	public Date getCgUpdtDate() {
		return cgUpdtDate;
	}

	public void setCgUpdtDate(Date cgUpdtDate) {
		this.cgUpdtDate = cgUpdtDate;
		this.cgUpdtDateStr = Constants.DATETIME_FORMAT.format(cgUpdtDate);
	}

	public String getCgUpdtDateStr() {
		return cgUpdtDateStr;
	}

	public int getCgUseNy() {
		return cgUseNy;
	}
	
	public void setCgUseNy(int cgUseNy) {
		this.cgUseNy = cgUseNy;
		this.cgUseNyStr = (cgUseNy == 1) ? "Y" : "N";
	}
	
	public String getCgUseNyStr() {
		return cgUseNyStr;
	}

	public int getcCount() {
		return cCount;
	}

	public void setcCount(int cCount) {
		this.cCount = cCount;
	}
	
}
