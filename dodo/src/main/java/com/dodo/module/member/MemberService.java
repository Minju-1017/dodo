package com.dodo.module.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.module.game.GameDto;

@Service
public class MemberService {
	
	@Autowired
	MemberDao dao;
	
	public int selectListCount(MemberVo vo) {
		return dao.selectListCount(vo);
	}
	
	public List<MemberDto> selectList(MemberVo vo) {
		return dao.selectList(vo);
	}
	
	public MemberDto selectOne(MemberDto memberDto) {
		return dao.selectOne(memberDto);
	}
	
	public MemberDto selectSignInMember(MemberDto memberDto) {
		return dao.selectSignInMember(memberDto);
	}
	
	public int insertCheckId(MemberDto memberDto) {
		return dao.insertCheckId(memberDto);
	}
	
	public int insert(MemberDto memberDto) {
		return dao.insert(memberDto);
	}
	
	public int update(MemberDto memberDto) {
		return dao.update(memberDto);
	}
	
	public int updateForgotPwd(MemberDto memberDto) {
		return dao.updateForgotPwd(memberDto);
	}
	
	public MemberDto updatePwdCheck(MemberDto memberDto) {
		return dao.updatePwdCheck(memberDto);
	}
	
	public int updatePwd(MemberDto memberDto) {
		return dao.updatePwd(memberDto);
	}
	
	public int delete(MemberDto memberDto) {
		return dao.delete(memberDto);
	}
	
	public int uelete(MemberDto memberDto) {
		return dao.uelete(memberDto);
	}
	
	public int listDelete(List<String> seqList) {
		return dao.listDelete(seqList);
	}
	
	public int listUelete(List<String> seqList) {
		return dao.listUelete(seqList);
	}
	
	public int selectWishListCount(MemberWishVo vo) {
		return dao.selectWishListCount(vo);
	}
	
	public List<MemberWishDto> selectWishList(MemberWishVo vo) {
		return dao.selectWishList(vo);
	}
	
	public int insertWish(MemberWishDto memberWishDto) {
		return dao.insertWish(memberWishDto);
	}
	
	public int deleteWishByCondition(MemberWishDto memberWishDto) {
		return dao.deleteWishByCondition(memberWishDto);
	}
	
	public int deleteWishBySeq(MemberWishDto memberWishDto) {
		return dao.deleteWishBySeq(memberWishDto);
	}
	
	public int listDeleteWish(List<String> seqList) {
		return dao.listDeleteWish(seqList);
	}
	
	public int selectHoldListCount(MemberHoldVo vo) {
		return dao.selectHoldListCount(vo);
	}
	
	public List<MemberHoldDto> selectHoldList(MemberHoldVo vo) {
		return dao.selectHoldList(vo);
	}
	
	public MemberHoldDto selectHoldOne(MemberHoldVo vo) {
		return dao.selectHoldOne(vo);
	}
	
	public int insertHoldCheck(MemberHoldDto memberHoldDto) {
		return dao.insertHoldCheck(memberHoldDto);
	}
	
	public int insertHold(MemberHoldDto memberHoldDto) {
		return dao.insertHold(memberHoldDto);
	}
	
	public int updateHold(MemberHoldDto memberHoldDto) {
		return dao.updateHold(memberHoldDto);
	}
	
	public int deleteHold(MemberHoldDto memberHoldDto) {
		return dao.deleteHold(memberHoldDto);
	}
	
	public int listDeleteHold(List<String> seqList) {
		return dao.listDeleteHold(seqList);
	}
	
}
