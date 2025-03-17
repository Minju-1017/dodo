package com.dodo.module.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeController {
	
	@Autowired
	CodeService codeService;
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/xdm/code/CodeXdmList")
	public String codeXdmList(Model model, CodeVo vo) throws Exception {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(codeService.selectOneCount());
		
		model.addAttribute("codeList", codeService.selectList(vo));
		model.addAttribute("vo", vo);
		
		return "xdm/code/CodeXdmList";
	}
	
	/**
	 * 조건에 맞는 데이터 1줄만 읽어오기
	 * @param model
	 * @param codeDto html에서 호출되는 파라메터와 일치하는 값이 있다면, 자동으로 바인딩 된다.
	 * @return
	 */
	@RequestMapping(value = "/xdm/code/CodeXdmItem")
	public String codeXdmItem(Model model, CodeDto codeDto) {
		model.addAttribute("codeItem", codeService.selectOne(codeDto));
		
		return "xdm/code/CodeXdmItem";
	}
	
	/**
	 * 데이터 입력 폼
	 * @return
	 */
	@RequestMapping(value = "/xdm/code/CodeXdmForm")
	public String codeXdmForm() {
		return "xdm/code/CodeXdmForm";
	}
	
	/**
	 * 입력한 데이터 추가하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "/xdm/code/CodeXdmInst")
	public String codeXdmInst(CodeDto codeDto) {
		codeService.insert(codeDto);
		
		return "redirect:/xdm/code/CodeXdmList";
	}
	
	/**
	 * 데이터 수정 폼
	 * 데이터 1개 읽어와서 화면에 보여주기
	 * @return
	 */
	@RequestMapping(value = "/xdm/code/CodeXdmMfom")
	public String codeXdmMfom(Model model, CodeDto codeDto) {		
		model.addAttribute("codeItem", codeService.selectOne(codeDto));
		
		return "xdm/code/CodeXdmMfom";
	}
	
	/**
	 * 입력한 데이터 수정하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "/xdm/code/CodeXdmUpdt")
	public String codeXdmUpdt(CodeDto codeDto) {
		codeService.update(codeDto);	

		return "redirect:/xdm/code/CodeXdmList";
	}
	
	/**
	 * 데이터 삭제하기
	 * @return redirect: 데이터 삭제 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "/xdm/code/CodeXdmDele")
	public String codeXdmDele(CodeDto codeDto) {
		codeService.delete(codeDto);	

		return "redirect:/xdm/code/CodeXdmList";
	}
	
	/**
	 * 데이터 삭제 옵션 세팅 - update 이용
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "/xdm/code/CodeXdmUele")
	public String codeXdmUele(CodeDto codeDto) {
		codeService.uelete(codeDto);	

		return "redirect:/xdm/code/CodeXdmList";
	}

}
