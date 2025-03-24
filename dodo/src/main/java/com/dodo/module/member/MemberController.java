package com.dodo.module.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodo.module.code.CodeService;

@Controller
@RequestMapping(value="/xdm/member/")
public class MemberController {

	private String path = "xdm/member/";
	
	@Autowired
	MemberService service;
	
	@Autowired
	CodeService codeService;
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "MemberXdmList")
	public String memberXdmList(Model model, @ModelAttribute("vo") MemberVo vo) throws Exception {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectOneCount(vo));

		if (vo.getTotalRows() > 0) {
			model.addAttribute("memberList", service.selectList(vo));
		}
		
		return path + "MemberXdmList";
	}
	
	/**
	 * 조건에 맞는 데이터 1줄만 읽어오기
	 * @param model
	 * @param Dto html에서 호출되는 파라메터와 일치하는 값이 있다면, 자동으로 바인딩 된다.
	 * @return
	 */
	@RequestMapping(value = "MemberXdmItem")
	public String memberXdmItem(Model model, MemberDto memberDto) {
		model.addAttribute("memberItem", service.selectOne(memberDto));
		
		return path + "MemberXdmItem";
	}
	
	/**
	 * 데이터 수정 폼
	 * 데이터 1개 읽어와서 화면에 보여주기
	 * @return
	 */
	@RequestMapping(value = "MemberXdmMfom")
	public String memberXdmMfom(Model model, MemberDto memberDto) {		
		model.addAttribute("memberItem", service.selectOne(memberDto));
		
		return path + "MemberXdmMfom";
	}
	
	/**
	 * 입력한 데이터 수정하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberXdmUpdt")
	public String memberXdmUpdt(MemberDto memberDto) {
		service.update(memberDto);	

		return "redirect:MemberXdmList";
	}
	
	/**
	 * 데이터 삭제하기
	 * @return redirect: 데이터 삭제 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberXdmDele")
	public String memberXdmDele(MemberDto memberDto) {
		service.delete(memberDto);	

		return "redirect:MemberXdmList";
	}
	
	/**
	 * 데이터 삭제 옵션 세팅 - update 이용
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberXdmUele")
	public String memberXdmUele(MemberDto memberDto) {
		service.uelete(memberDto);	

		return "redirect:MemberXdmList";
	}

}
