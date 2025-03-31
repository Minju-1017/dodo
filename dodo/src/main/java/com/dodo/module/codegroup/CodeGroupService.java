package com.dodo.module.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGroupService {
	
	@Autowired
	CodeGroupDao dao;
	
	public int selectOneCount(CodeGroupVo vo) {
		return dao.selectOneCount(vo);
	}
	
	public List<CodeGroupDto> selectList(CodeGroupVo vo) {
		return dao.selectList(vo);
	}
	
	public List<CodeGroupDto> selectListWithoutPaging() {
		return dao.selectListWithoutPaging();
	}
	
	public CodeGroupDto selectOne(CodeGroupDto codeGroupDto) {
		return dao.selectOne(codeGroupDto);
	}
	
	public int insert(CodeGroupDto codeGroupDto) {
		return dao.insert(codeGroupDto);
	}
	
	public int update(CodeGroupDto codeGroupDto) {
		return dao.update(codeGroupDto);
	}
	
	public int delete(CodeGroupDto codeGroupDto) {
		return dao.delete(codeGroupDto);
	}
	
	public int uelete(CodeGroupDto codeGroupDto) {
		return dao.uelete(codeGroupDto);
	}
	
	public int listDelete(List<String> seqList) {
		return dao.listDelete(seqList);
	}
	
	public int listUelete(List<String> seqList) {
		return dao.listUelete(seqList);
	}
}
