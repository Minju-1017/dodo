package com.dodo.module.code;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dodo.module.codegroup.CodeGroupDto;

@Repository
public interface CodeDao {

	public int selectOneCount();
	public List<CodeDto> selectList(CodeVo vo); 
	public CodeDto selectOne(CodeDto codeDto);
	public int insert(CodeDto codeDto);
	public int update(CodeDto codeDto);
	public int delete(CodeDto codeDto);
	public int uelete(CodeDto codeDto);
	
	public List<CodeGroupDto> selectCodeGroupList(); 
}
