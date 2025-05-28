package com.dodo.module.member;

import com.dodo.module.game.GameDto;

public class MemberHoldDto extends GameDto {
	
	private String mhSeq;
	private String member_mSeq;
	private String game_gSeq;
	private String mhSleeve;
	private String mhCoin;
	private String mhMemo;
	
	public String getMhSeq() {
		return mhSeq;
	}

	public void setMhSeq(String mhSeq) {
		this.mhSeq = mhSeq;
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

	public String getMhSleeve() {
		return mhSleeve;
	}

	public void setMhSleeve(String mhSleeve) {
		this.mhSleeve = mhSleeve;
	}

	public String getMhCoin() {
		return mhCoin;
	}

	public void setMhCoin(String mhCoin) {
		this.mhCoin = mhCoin;
	}

	public String getMhMemo() {
		return mhMemo;
	}

	public void setMhMemo(String mhMemo) {
		this.mhMemo = mhMemo;
	}

}

