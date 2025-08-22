package com.dodo.module.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Postman 설치 하고 테스트 하기

@RestController
@RequestMapping("/rest/member")
public class MemberRestController {

	@Autowired
	MemberService service;

//	Postman Test - GET 선택, http://localhost:8080/rest/member
//	@RequestMapping(value = "", method = RequestMethod.GET)
	@GetMapping("")
	public List<MemberDto> selectList(MemberVo vo) throws Exception {
		List<MemberDto> list = service.selectList(vo);
		return list;
	}
	

//	Postman Test - GET 선택, http://localhost:8080/rest/member/1
// 	이 방식 사용하려면 build.gradle에 -parameters 플래그 활성화 해줘야 한다.
//	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	@GetMapping("/{seq}")
	public MemberDto selectOne(@PathVariable("seq") String seq, MemberDto dto) throws Exception {
		dto.setmSeq(seq);
		MemberDto item = service.selectOne(dto);
		
		return item;
	}
	

//	Postman Test - Post 선택, http://localhost:8080/rest/member
//	@RequestMapping(value = "", method = RequestMethod.POST)
	@PostMapping("")
	public String insert(MemberDto dto) throws Exception {
		service.insert(dto);
		return dto.getmSeq();
	}
	
//	Postman Test - Put/Patch 선택, http://localhost:8080/rest/member/1
//	@RequestMapping(value = "/{seq}", method = RequestMethod.PUT)
//	@PatchMapping("/{seq}")
	@PutMapping("/{seq}")
	public void update(@PathVariable("seq") String seq, MemberDto dto) throws Exception {
		dto.setmSeq(seq);
		service.update(dto);
	}
	
}
