package com.dodo.module.member;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {

	public int selectListCount(MemberVo vo);
	public List<MemberDto> selectList(MemberVo vo);  
	public MemberDto selectOne(MemberDto memberDto);
	public MemberDto selectSignInMember(MemberDto memberDto);
	public int insertCheckId(MemberDto memberDto);
	public int insert(MemberDto memberDto);
	public int update(MemberDto memberDto);
	public int updateForgotPwd(MemberDto memberDto);
	public MemberDto updatePwdCheck(MemberDto memberDto);
	public int updatePwd(MemberDto memberDto);
	public int delete(MemberDto memberDto);
	public int uelete(MemberDto memberDto);
	public int listDelete(List<String> seqList);
	public int listUelete(List<String> seqList);
	
	public int selectWishListCount(MemberWishVo vo);
	public List<MemberWishDto> selectWishList(MemberWishVo vo);  
	public int insertWish(MemberWishDto memberWishDto);
	public int deleteWishByCondition(MemberWishDto memberWishDto);
	public int deleteWishBySeq(MemberWishDto memberWishDto);
	public int listDeleteWish(List<String> seqList);
	
	public int selectHoldListCount(MemberHoldVo vo);
	public List<MemberHoldDto> selectHoldList(MemberHoldVo vo);  
	public MemberHoldDto selectHoldOne(MemberHoldVo vo); 
	public int insertHoldCheck(MemberHoldDto memberHoldDto);
	public int insertHold(MemberHoldDto memberHoldDto);  
	public int updateHold(MemberHoldDto memberHoldDto); 
	public int deleteHoldByCondition(MemberHoldDto memberHoldDto);  
	public int deleteHoldBySeq(MemberHoldDto memberHoldDto);  
	public int listDeleteHold(List<String> seqList); 
		
}
