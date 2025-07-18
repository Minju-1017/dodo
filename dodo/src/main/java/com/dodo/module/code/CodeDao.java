package com.dodo.module.code;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CodeDao {

	public List<CodeDto> selectListCachedCodeArrayList();
	
	public int selectListCount(CodeVo vo);
	public List<CodeDto> selectList(CodeVo vo); 
	public List<CodeDto> selectListWithoutPaging(CodeDto codeDto); 
	public CodeDto selectOne(CodeDto codeDto);
	public int insert(CodeDto codeDto);
	public int update(CodeDto codeDto);
	public int delete(CodeDto codeDto);
	public int uelete(CodeDto codeDto);
	public int listDelete(List<String> seqList);
	public int listUelete(List<String> seqList);
	
	/* Code Group 데이터는 Code Group 쪽에서 가져오도록 처리
	public List<CodeGroupDto> selectCodeGroupList(); 
	*/
}
