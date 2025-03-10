package com.dodo.module.codegroup;

public class CodeGroupDto {
	
	private String seq;
	private String code;
	private String name;
	private int count;
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
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
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
