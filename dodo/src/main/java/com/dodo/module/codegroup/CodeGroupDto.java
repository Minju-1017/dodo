package com.dodo.module.codegroup;

public class CodeGroupDto {
	
	private String seq;
	private String code;
	private String name;
	private String nameEng;
	private int count;
	private int sequence;
	private String description;
	private int useNy;
	private String useNyStr;
	private int delNy;

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

	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUseNy() {
		return useNy;
	}
	
	public void setUseNy(int useNy) {
		this.useNy = useNy;
		this.useNyStr = (useNy == 1) ? "Y" : "N";
	}
	
	public String getUseNyStr() {
		return useNyStr;
	}
	
	public int getDelNy() {
		return delNy;
	}
	
	public void setDelNy(int delNy) {
		this.delNy = delNy;
	}
	
}
