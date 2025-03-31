package com.dodo.module.member;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {

	public int selectOneCount(MemberVo vo);
	public List<MemberDto> selectList(MemberVo vo);  
	public MemberDto selectOne(MemberDto memberDto);
	public MemberDto selectSignInMember(MemberDto memberDto);
	public int insert(MemberDto memberDto);
	public int update(MemberDto memberDto);
	public int delete(MemberDto memberDto);
	public int uelete(MemberDto memberDto);
	public int listDelete(List<String> seqList);
	public int listUelete(List<String> seqList);
	
}
