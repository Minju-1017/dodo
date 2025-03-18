package com.dodo.module.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	MemberDao memberDao;
	
	public int selectOneCount() {
		return memberDao.selectOneCount();
	}
	
	public List<MemberDto> selectList(MemberVo vo) {
		return memberDao.selectList(vo);
	}
	
	public List<MemberDto> selectListWithoutPaging() {
		return memberDao.selectListWithoutPaging();
	}
	
	public MemberDto selectOne(MemberDto memberDto) {
		return memberDao.selectOne(memberDto);
	}
	
	public int insert(MemberDto memberDto) {
		return memberDao.insert(memberDto);
	}
	
	public int update(MemberDto memberDto) {
		return memberDao.update(memberDto);
	}
	
	public int delete(MemberDto memberDto) {
		return memberDao.delete(memberDto);
	}
	
	public int uelete(MemberDto memberDto) {
		return memberDao.uelete(memberDto);
	}
}
