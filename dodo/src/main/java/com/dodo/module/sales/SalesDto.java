package com.dodo.module.sales;

public class SalesDto extends SalesOrderDto {
	
	private String msSeq;
	private String member_mSeq;
	private String game_gSeq;
	private String msTitle;
	private String msDesc;
	private int msPrice;
	private int msFee;
	private int msStateCd;
	private int msHit;
	private String msRegiDate;
	private String msUpdtDate;
	
	private String gName;
	private Integer gCategoryCd;
	
	private String mName;
	private String fPath;
	
	public String getMsSeq() {
		return msSeq;
	}
	
	public void setMsSeq(String msSeq) {
		this.msSeq = msSeq;
	}
	
	public String getMember_mSeq() {
		return member_mSeq;
	}
	
	public void setMember_mSeq(String member_mSeq) {
		this.member_mSeq = member_mSeq;
	}
	
	public String getGame_gSeq() {
		return game_gSeq;
	}
	
	public void setGame_gSeq(String game_gSeq) {
		this.game_gSeq = game_gSeq;
	}
	
	public String getMsTitle() {
		return msTitle;
	}
	
	public void setMsTitle(String msTitle) {
		this.msTitle = msTitle;
	}
	
	public String getMsDesc() {
		return msDesc;
	}
	
	public void setMsDesc(String msDesc) {
		this.msDesc = msDesc;
	}
	
	public int getMsPrice() {
		return msPrice;
	}
	
	public void setMsPrice(int msPrice) {
		this.msPrice = msPrice;
	}
	
	public int getMsFee() {
		return msFee;
	}
	
	public void setMsFee(int msFee) {
		this.msFee = msFee;
	}
	
	public int getMsStateCd() {
		return msStateCd;
	}
	
	public void setMsStateCd(int msStateCd) {
		this.msStateCd = msStateCd;
	}
	
	public int getMsHit() {
		return msHit;
	}
	
	public void setMsHit(int msHit) {
		this.msHit = msHit;
	}
	
	public String getMsRegiDate() {
		return msRegiDate;
	}
	
	public void setMsRegiDate(String msRegiDate) {
		this.msRegiDate = msRegiDate;
	}
	
	public String getMsUpdtDate() {
		return msUpdtDate;
	}
	
	public void setMsUpdtDate(String msUpdtDate) {
		this.msUpdtDate = msUpdtDate;
	}

	public Integer getgCategoryCd() {
		return gCategoryCd;
	}

	public void setgCategoryCd(Integer gCategoryCd) {
		this.gCategoryCd = gCategoryCd;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getfPath() {
		return fPath;
	}

	public void setfPath(String fPath) {
		this.fPath = fPath;
	}
	
}
