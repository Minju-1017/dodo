package com.dodo.module.game;

public class GameReviewDto {
	
	private String grSeq;
	private String member_mSeq;
	private String game_gSeq;
	private double grScore;
	private String grComment;
	private String grRegiDate;
	
	private String gName;
	
	private String mId;
	private String mName;
	
	private String fPath;
	
	// 평점 별 표시를 위한 값
	private int fillStarCount; 	// 다 채운 별
	private int harfStarCount; 	// 반 채운 별
	private int emptyStarCount; // 빈 별
	
	public String getGrSeq() {
		return grSeq;
	}
	
	public void setGrSeq(String grSeq) {
		this.grSeq = grSeq;
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
	
	public double getGrScore() {
		return grScore;
	}
	
	public void setGrScore(double grScore) {
		this.grScore = grScore;
		
		// 별표 셋팅
		double score = grScore / 2.0;
		this.fillStarCount = (int) score;
		this.harfStarCount = (score - this.fillStarCount) > 0 ? 1 : 0;
		this.emptyStarCount = 5 - this.fillStarCount - this.harfStarCount;
	}
	
	public String getGrComment() {
		return grComment;
	}
	
	public void setGrComment(String grComment) {
		this.grComment = grComment;
	}
	
	public String getGrRegiDate() {
		return grRegiDate;
	}
	
	public void setGrRegiDate(String grRegiDate) {
		this.grRegiDate = grRegiDate;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
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

	public int getFillStarCount() {
		return fillStarCount;
	}

	public void setFillStarCount(int fillStarCount) {
		this.fillStarCount = fillStarCount;
	}

	public int getHarfStarCount() {
		return harfStarCount;
	}

	public void setHarfStarCount(int harfStarCount) {
		this.harfStarCount = harfStarCount;
	}

	public int getEmptyStarCount() {
		return emptyStarCount;
	}

	public void setEmptyStarCount(int emptyStarCount) {
		this.emptyStarCount = emptyStarCount;
	}
	
}
