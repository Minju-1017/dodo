package com.dodo.module.member;

public class MemberDto {
	
	private String mSeq;
	private String mId;
	private String mName;
	private String mEmail;
	private String mPwd;
	private String mBirth;
	private int mGenderCd;
	private String mPfFileName;
	private int mGradeCd;
	private String mRegiDate;
	private String mUpdtDate;
	private int mDelNy;
	private String mDelNyStr;

	public String getmSeq() {
		return mSeq;
	}
	
	public void setmSeq(String mSeq) {
		this.mSeq = mSeq;
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
	
	public String getmEmail() {
		return mEmail;
	}
	
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	
	public String getmPwd() {
		return mPwd;
	}
	
	public void setmPwd(String mPwd) {
		this.mPwd = mPwd;
	}
	
	public String getmBirth() {
		return mBirth;
	}
	
	public void setmBirth(String mBirth) {
		this.mBirth = mBirth;
	}

	public int getmGenderCd() {
		return mGenderCd;
	}
	
	public void setmGenderCd(int mGenderCd) {
		this.mGenderCd = mGenderCd;
	}

	public String getmPfFileName() {
		return mPfFileName;
	}
	
	public void setmPfFileName(String mPfFileName) {
		this.mPfFileName = mPfFileName;
	}

	public int getmGradeCd() {
		return mGradeCd;
	}

	public void setmGradeCd(int mGradeCd) {
		this.mGradeCd = mGradeCd;
	}

	public String getmRegiDate() {
		return mRegiDate;
	}

	public void setmRegiDate(String mRegiDate) {
		this.mRegiDate = mRegiDate;
	}

	public String getmUpdtDate() {
		return mUpdtDate;
	}

	public void setmUpdtDate(String mUpdtDate) {
		this.mUpdtDate = mUpdtDate;
	}

	public int getmDelNy() {
		return mDelNy;
	}

	public void setmDelNy(int mDelNy) {
		this.mDelNy = mDelNy;
		this.mDelNyStr = mDelNy == 1 ? "Y" : "N";
	}

	public String getmDelNyStr() {
		return mDelNyStr;
	}
	
}
