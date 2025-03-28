package com.dodo.module.member;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.dodo.module.Constants;

public class MemberDto {
	
	private String mSeq;
	private String mId;
	private String mName;
	private String mEmail;
	private String mPwd;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date mBirth;
	private String mBirthStr;
	
	private int mGenderCd;
	private String mPfFileName;
	private int mGradeCd;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date mRegiDate;
	private String mRegiDateStr;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date mUpdtDate;
	private String mUpdtDateStr;
	
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
	
	public Date getmBirth() {
		return mBirth;
	}
	
	public void setmBirth(Date mBirth) {
		this.mBirth = mBirth;
		this.mBirthStr = Constants.DATE_FORMAT.format(mBirth);
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

	public Date getmRegiDate() {
		return mRegiDate;
	}

	public void setmRegiDate(Date mRegiDate) {
		this.mRegiDate = mRegiDate;
		this.mRegiDateStr = Constants.DATETIME_FORMAT.format(mRegiDate);
	}

	public String getmRegiDateStr() {
		return mRegiDateStr;
	}

	public Date getmUpdtDate() {
		return mUpdtDate;
	}

	public void setmUpdtDate(Date mUpdtDate) {
		this.mUpdtDate = mUpdtDate;
		this.mUpdtDateStr = Constants.DATETIME_FORMAT.format(mUpdtDate);
	}

	public String getmUpdtDateStr() {
		return mUpdtDateStr;
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
