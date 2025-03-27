package com.dodo.module.code;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.module.member.MemberDto;

import jakarta.annotation.PostConstruct;

@Service
public class CodeService {
	
	@Autowired
	CodeDao dao;
	
	@PostConstruct //<-- 시스템 구동될 때 한번 실행됨
	public void selectListCachedCodeArrayList() throws Exception {
		List<CodeDto> codeListFromDb = (ArrayList<CodeDto>) dao.selectListCachedCodeArrayList();
		
		CodeDto.cachedCodeArrayList.clear(); 
		CodeDto.cachedCodeArrayList.addAll(codeListFromDb);
		
		System.out.println("cachedCodeArrayList: " + CodeDto.cachedCodeArrayList.size() + " chached !");
	}
    
    
	public static void clear() throws Exception {
		CodeDto.cachedCodeArrayList.clear();
	}
	
	
	public static List<CodeDto> selectListCachedCode(String codeGroup_cgSeq) throws Exception {
		List<CodeDto> codeList = new ArrayList<CodeDto>();
		
		for(CodeDto code : CodeDto.cachedCodeArrayList) {
			if (code.getCodeGroup_cgSeq().equals(codeGroup_cgSeq)) {
				codeList.add(code);
			}
		}
		
		return codeList;
	}
	
	public static String selectOneCachedCode(String cSeq) throws Exception {
		String codeName = "";
		
		for(CodeDto code : CodeDto.cachedCodeArrayList) {
			if (code.getcSeq().equals(cSeq)) {
				codeName = code.getcName();
			}
		}
		return codeName;
	}
	//-->
	
	public int selectOneCount(CodeVo vo) {
		return dao.selectOneCount(vo);
	}
	
	public List<CodeDto> selectList(CodeVo vo) {
		return dao.selectList(vo);
	}
	public List<CodeDto> selectListWithoutPaging(MemberDto memberDto) {
		return dao.selectListWithoutPaging(memberDto);
	}
	
	public CodeDto selectOne(CodeDto codeDto) {
		return dao.selectOne(codeDto);
	}
	
	public int insert(CodeDto codeDto) {
		return dao.insert(codeDto);
	}
	
	public int update(CodeDto codeDto) {
		return dao.update(codeDto);
	}
	
	public int delete(CodeDto codeDto) {
		return dao.delete(codeDto);
	}
	
	public int uelete(CodeDto codeDto) {
		return dao.uelete(codeDto);
	}
	
	public int listDelete(List<CodeDto> codeListDto) {
		return dao.listDelete(codeListDto);
	}
	
	public int listUelete(List<CodeDto> codeListDto) {
		return dao.listUelete(codeListDto);
	}
	
	/* Code Group 데이터는 Code Group 쪽에서 가져오도록 처리
	public List<CodeGroupDto> selectCodeGroupList() {
		return codeDao.selectCodeGroupList();
	}
	*/
}
