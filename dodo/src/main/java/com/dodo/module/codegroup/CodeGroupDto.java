package com.dodo.module.codegroup;

public class CodeGroupDto {
	
	private String seq;
	private String cgName;
	private String cgNameEng;
	private int cgCount;
	private int cgSequence;
	private String cgDescription;
	private int cgUseNy;
	private String cgUseNyStr;
	private int cgDelNy;
	
	public String getSeq() {
		return seq;
	}
	
	public void setSeq(String seq) {
		this.seq = seq;
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
	
	public int getCgCount() {
		return cgCount;
	}
	
	public void setCgCount(int cgCount) {
		this.cgCount = cgCount;
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
	
	public int getCgDelNy() {
		return cgDelNy;
	}
	
	public void setCgDelNy(int cgDelNy) {
		this.cgDelNy = cgDelNy;
	}
	
}
