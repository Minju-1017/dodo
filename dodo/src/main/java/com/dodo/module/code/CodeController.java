package com.dodo.module.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodo.module.codegroup.CodeGroupDto;
import com.dodo.module.codegroup.CodeGroupService;
import com.dodo.module.codegroup.CodeGroupVo;

@Controller
@RequestMapping(value="/xdm/code/")
public class CodeController {
	
	private String path = "xdm/code/";
	
	@Autowired
	CodeService service;
	
	@Autowired
	CodeGroupService codeGroupService;
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "CodeXdmList")
	public String codeXdmList(Model model, @ModelAttribute("vo") CodeVo vo) throws Exception {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("codeList", service.selectList(vo));
		}
		
		return path + "CodeXdmList";
	}
	
	/**
	 * 데이터 입력/수정 폼
	 * @return
	 */
	@RequestMapping(value = "CodeXdmForm")
	public String codeXdmForm(@ModelAttribute("vo") CodeVo vo, Model model, CodeDto codeDto) 
			throws Exception {	
		/* 
			Code Group쪽에서 데이터를 가져올 때, 아래 주석처럼 Code쪽에서 DB로 접근해서 가져와도 되고
			@Autowired로 CodeGroupService codeGroupService;를 선언해서 가져와도 된다.
			
			// model.addAttribute("codeGroupList", service.selectCodeGroupList());
		*/
		
		model.addAttribute("codeGroupList", codeGroupService.selectListWithoutPaging());
		
		if (vo.getcSeq().equals("0") || vo.getcSeq().equals("")) {
			// insert mode
		} else {
			// update mode
			model.addAttribute("codeItem", service.selectOne(codeDto));
		}
		
		return path + "CodeXdmForm";
	}
	
	/**
	 * 입력한 데이터 추가하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "CodeXdmInst")
	public String codeXdmInst(CodeDto codeDto) {
		service.insert(codeDto);
		
		return "redirect:CodeXdmList";
	}
	
	/**
	 * 입력한 데이터 수정하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "CodeXdmUpdt")
	public String codeXdmUpdt(CodeDto codeDto) {
		service.update(codeDto);	

		return "redirect:CodeXdmList";
	}
	
	/**
	 * 데이터 삭제하기
	 * @return redirect: 데이터 삭제 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "CodeXdmDele")
	public String codeXdmDele(CodeDto codeDto) {
		service.delete(codeDto);	

		return "redirect:CodeXdmList";
	}
	
	/**
	 * 데이터 삭제 옵션 세팅 - update 이용
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "CodeXdmUele")
	public String codeXdmUele(CodeDto codeDto) {
		service.uelete(codeDto);	

		return "redirect:CodeXdmList";
	}

}
