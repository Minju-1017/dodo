package com.dodo.module.code;

public class CodeDto {
	
	private String seq;
	private String cName;
	private String cNameEng;
	private int cSequence;
	private String cDescription;
	private int cUseNy;
	private String cUseNyStr;
	private int cDelNy;
	private String codeGroup_seq;
	private String cgName;
	
	public String getSeq() {
		return seq;
	}
	
	public void setSeq(String seq) {
		this.seq = seq;
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
	
	public void setcUseNyStr(String cUseNyStr) {
		
	}
	
	public int getcDelNy() {
		return cDelNy;
	}
	
	public void setcDelNy(int cDelNy) {
		this.cDelNy = cDelNy;
	}
	
	public String getCodeGroup_seq() {
		return codeGroup_seq;
	}
	
	public void setCodeGroup_seq(String codeGroup_seq) {
		this.codeGroup_seq = codeGroup_seq;
	}

	public String getCgName() {
		return cgName;
	}

	public void setCgName(String cgName) {
		this.cgName = cgName;
	}
	
}
