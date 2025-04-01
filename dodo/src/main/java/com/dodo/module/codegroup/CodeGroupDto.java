package com.dodo.module.codegroup;

public class CodeGroupDto {
	
	private String cgSeq;
	private String cgName;
	private String cgNameEng;
	private int cgSequence;
	private String cgDescription;
	private String cgRegiDate;
	private String cgUpdtDate;
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
	
	public String getCgRegiDate() {
		return cgRegiDate;
	}

	public void setCgRegiDate(String cgRegiDate) {
		this.cgRegiDate = cgRegiDate;
	}

	public String getCgUpdtDate() {
		return cgUpdtDate;
	}

	public void setCgUpdtDate(String cgUpdtDate) {
		this.cgUpdtDate = cgUpdtDate;
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
