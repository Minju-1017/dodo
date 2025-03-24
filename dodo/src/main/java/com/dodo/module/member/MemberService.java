package com.dodo.module.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	MemberDao dao;
	
	public int selectOneCount(MemberVo vo) {
		return dao.selectOneCount(vo);
	}
	
	public List<MemberDto> selectList(MemberVo vo) {
		return dao.selectList(vo);
	}
	
	public MemberDto selectOne(MemberDto memberDto) {
		return dao.selectOne(memberDto);
	}
	
	public int insert(MemberDto memberDto) {
		return dao.insert(memberDto);
	}
	
	public int update(MemberDto memberDto) {
		return dao.update(memberDto);
	}
	
	public int delete(MemberDto memberDto) {
		return dao.delete(memberDto);
	}
	
	public int uelete(MemberDto memberDto) {
		return dao.uelete(memberDto);
	}
}
