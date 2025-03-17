package com.dodo.module.code;

public class CodeDto {
	
	private String seq;
	private String code;
	private String name;
	private String nameEng;
	private int sequence;
	private int useNy;
	private String useNyStr;
	private int delNy;
	private String codeGroup_seq;
	
	public String getSeq() {
		return seq;
	}
	
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNameEng() {
		return nameEng;
	}
	
	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}
	
	public int getSequence() {
		return sequence;
	}
	
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public int getUseNy() {
		return useNy;
	}
	
	public String getUseNyStr() {
		return useNyStr;
	}
	
	public void setUseNy(int useNy) {
		this.useNy = useNy;
		this.useNyStr = (useNy == 1) ? "Y" : "N";
	}
	
	public int getDelNy() {
		return delNy;
	}
	
	public void setDelNy(int delNy) {
		this.delNy = delNy;
	}
	
	public String getCodeGroup_seq() {
		return codeGroup_seq;
	}
	
	public void setCodeGroup_seq(String codeGroup_seq) {
		this.codeGroup_seq = codeGroup_seq;
	}
	
}
