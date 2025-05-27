package com.dodo.module.member;

import com.dodo.module.game.GameDto;

public class MemberWishDto extends GameDto {
	
	private String mwSeq;
	private String member_mSeq;
	private String game_gSeq;
	
	public String getMwSeq() {
		return mwSeq;
	}
	
	public void setMwSeq(String mwSeq) {
		this.mwSeq = mwSeq;
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

}
