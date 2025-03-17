package com.dodo.module.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.module.codegroup.CodeGroupDto;

@Service
public class CodeService {
	
	@Autowired
	CodeDao codeDao;
	
	public int selectOneCount() {
		return codeDao.selectOneCount();
	}
	
	public List<CodeDto> selectList(CodeVo vo) {
		return codeDao.selectList(vo);
	}
	
	public CodeDto selectOne(CodeDto codeDto) {
		return codeDao.selectOne(codeDto);
	}
	
	public int insert(CodeDto codeDto) {
		return codeDao.insert(codeDto);
	}
	
	public int update(CodeDto codeDto) {
		return codeDao.update(codeDto);
	}
	
	public int delete(CodeDto codeDto) {
		return codeDao.delete(codeDto);
	}
	
	public int uelete(CodeDto codeDto) {
		return codeDao.uelete(codeDto);
	}
	
	public List<CodeGroupDto> selectCodeGroupList() {
		return codeDao.selectCodeGroupList();
	}
}
