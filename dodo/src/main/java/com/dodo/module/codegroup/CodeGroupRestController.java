package com.dodo.module.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Postman 설치 하고 테스트 하기

@RestController
@RequestMapping("/rest/codegroup")
public class CodeGroupRestController {

	@Autowired
	CodeGroupService service;

//	Postman Test - GET 선택, http://localhost:8080/rest/codegroup
//	@RequestMapping(value = "", method = RequestMethod.GET)
	@GetMapping("")
	public List<CodeGroupDto> selectList(CodeGroupVo vo) throws Exception {
		List<CodeGroupDto> list = service.selectList(vo);
		return list;
	}
	

//	Postman Test - GET 선택, http://localhost:8080/rest/codegroup/40
// 	이 방식 사용하려면 build.gradle에 -parameters 플래그 활성화 해줘야 한다.
//	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	@GetMapping("/{seq}")
	public CodeGroupDto selectOne(@PathVariable("seq") String seq, CodeGroupDto dto) throws Exception {
		dto.setCgSeq(seq);
		CodeGroupDto item = service.selectOne(dto);
		
		return item;
	}
	

//	Postman Test - Post 선택, http://localhost:8080/rest/codegroup
//	@RequestMapping(value = "", method = RequestMethod.POST)
	@PostMapping("")
	public String insert(CodeGroupDto dto) throws Exception {
		service.insert(dto);
		return dto.getCgSeq();
	}
	
//	Postman Test - Put/Patch 선택, http://localhost:8080/rest/codegroup/40
//	@RequestMapping(value = "/{seq}", method = RequestMethod.PUT)
//	@PatchMapping("/{seq}")
	@PutMapping("/{seq}")
	public void update(@PathVariable("seq") String seq, CodeGroupDto dto) throws Exception {
		dto.setCgSeq(seq);
		service.update(dto);
	}
	
}
