package com.dodo.module.codegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String codeGroupXdmList(Model model, CodeGroupVo vo) throws Exception {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectOneCount());
		
		model.addAttribute("codeGroupList", service.selectList(vo));
		model.addAttribute("vo", vo);
		
		return path + "CodeGroupXdmList";
	}
	
	/**
	 * 조건에 맞는 데이터 1줄만 읽어오기
	 * @param model
	 * @param codeListDto html에서 호출되는 파라메터와 일치하는 값이 있다면, 자동으로 바인딩 된다.
	 * @return
	 */
	@RequestMapping(value = "CodeGroupXdmItem")
	public String codeGroupXdmItem(Model model, CodeGroupDto codeGroupDto) {
		model.addAttribute("codeGroupItem", service.selectOne(codeGroupDto));
		
		return path + "CodeGroupXdmItem";
	}
	
	/**
	 * 데이터 입력 폼
	 * @return
	 */
	@RequestMapping(value = "CodeGroupXdmForm")
	public String codeGroupXdmForm() {
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
	 * 데이터 수정 폼
	 * 데이터 1개 읽어와서 화면에 보여주기
	 * @return
	 */
	@RequestMapping(value = "CodeGroupXdmMfom")
	public String codeGroupXdmMfom(Model model, CodeGroupDto codeGroupDto) {		
		model.addAttribute("codeGroupItem", service.selectOne(codeGroupDto));
		
		return path + "CodeGroupXdmMfom";
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
