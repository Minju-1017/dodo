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
	
}
