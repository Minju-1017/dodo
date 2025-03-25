package com.dodo.module.codegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/xdm/codegroup/")
public class CodeGroupController {
	
	private String path = "xdm/codegroup/";
	
	@Autowired
	CodeGroupService service;
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "CodeGroupXdmList")
	public String codeGroupXdmList(Model model, @ModelAttribute("vo") CodeGroupVo vo) throws Exception {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("codeGroupList", service.selectList(vo));
			// model.addAttribute("vo", vo); 	// 함수 파라메터로 정의된 아래 구문과 동일한 표현이라 주석처리 
												// @ModelAttribute("vo") CodeGroupVo vo
		}
		
		return path + "CodeGroupXdmList";
	}
	
	/**
	 * 데이터 입력/수정 폼
	 * @return
	 */
	@RequestMapping(value = "CodeGroupXdmForm")
	public String codeGroupXdmForm(@ModelAttribute("vo") CodeGroupVo vo,
			Model model, CodeGroupDto codeGroupDto) throws Exception {
		if (vo.getCgSeq().equals("0") || vo.getCgSeq().equals("")) {
			// insert mode
		} else {
			// update mode
			model.addAttribute("codeGroupItem", service.selectOne(codeGroupDto));
		}
		
		return path + "CodeGroupXdmForm";
	}
	
	/**
	 * 입력한 데이터 추가하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "CodeGroupXdmInst")
	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
		service.insert(codeGroupDto);
		
		return "redirect:CodeGroupXdmList";
	}
	
	/**
	 * 입력한 데이터 수정하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "CodeGroupXdmUpdt")
	public String codeGroupXdmUpdt(CodeGroupDto codeGroupDto) {
		service.update(codeGroupDto);	

		return "redirect:CodeGroupXdmList";
	}
	
	/**
	 * 데이터 삭제하기
	 * @return redirect: 데이터 삭제 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "CodeGroupXdmDele")
	public String codeGroupXdmDele(CodeGroupDto codeGroupDto) {
		service.delete(codeGroupDto);	

		return "redirect:CodeGroupXdmList";
	}
	
	/**
	 * 데이터 삭제 옵션 세팅 - update 이용
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "CodeGroupXdmUele")
	public String codeGroupXdmUele(CodeGroupDto codeGroupDto) {
		service.uelete(codeGroupDto);	

		return "redirect:CodeGroupXdmList";
	}

}
