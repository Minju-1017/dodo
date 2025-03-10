package com.dodo.module.codegroup;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CodeGroupDao {

		public List<CodeGroupDto> selectList(); 
		public CodeGroupDto selectOne(CodeGroupDto codeGroupDto);
		public int insert(CodeGroupDto codeGroupDto);
		public int update(CodeGroupDto codeGroupDto);
		public int delete(CodeGroupDto codeGroupDto);
		public int uelete(CodeGroupDto codeGroupDto);
}
