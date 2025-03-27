package com.dodo.module.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodo.module.Constants;
import com.dodo.module.code.CodeService;
import com.dodo.module.codegroup.CodeGroupDto;
import com.dodo.module.codegroup.CodeGroupVo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/xdm/member/")
public class MemberController {

	private String path = "xdm/member/";
	
	@Autowired
	MemberService service;
	
	@Autowired
	CodeService codeService;
	
	/**
	 * 로그인 화면 이동
	 * @return
	 */
	@RequestMapping(value = "MemberXdmSignIn")	
	public String memberXdmSignIn() throws Exception {				
		return path + "MemberXdmSignIn";
	}
	
	/**
	 * Ajax를 통한 로그인 처리
	 * @param memberDto
	 * @return
	 * @throws Exception
	 */
	@ResponseBody // Ajax 코드는 무조건 써준다.
	@RequestMapping(value = "MemberXdmSignInProc")
	public Map<String, Object> memberXdmSignInProc(MemberDto memberDto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		MemberDto mDto = service.selectSignInMember(memberDto);
		
		if (mDto == null) { // MyBatis에서 디비 검색 후 결과값이 없으면 NULL이 떨어짐
			httpSession.setAttribute("sessSeqXdm", null);
			returnMap.put("rt", "fail");
		} else {
			httpSession.setMaxInactiveInterval(Constants.SESSION_MINUTE_XDM);
			httpSession.setAttribute("sessSeqXdm", mDto.getmSeq());
			httpSession.setAttribute("sessIdXdm", mDto.getmId());
			httpSession.setAttribute("sessNameXdm", mDto.getmName());
			
			returnMap.put("rt", "success");
		}
		
		return returnMap;
	}
	
	/**
	 * Ajax를 통한 로그아웃 처리
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "MemberXdmSignOutProc")	
	public Map<String, Object> memberXdmSignOutProc(HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap.put("rt", "success");
		httpSession.setAttribute("sessSeqXdm", null);
		
		return returnMap;
	}
	
	/**
	 * 회원관리 - 전체 데이터 읽어오기(페이징 기능 들어감)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "MemberXdmList")
	public String memberXdmList(Model model, @ModelAttribute("vo") MemberVo vo,
			HttpSession httpSession) throws Exception {
		if (httpSession.getAttribute("sessSeqXdm") == null) {
			return "redirect:MemberXdmSignIn";
		}
		
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectOneCount(vo));

		if (vo.getTotalRows() > 0) {
			model.addAttribute("memberList", service.selectList(vo));
		}
		
		return path + "MemberXdmList";
	}
	
	/**
	 * 회원관리 - 데이터 추가/수정 폼
	 * @return
	 */
	@RequestMapping(value = "MemberXdmForm")
	public String memberXdmForm(@ModelAttribute("vo") MemberVo vo,
			Model model, MemberDto memberDto) throws Exception {
		if (vo.getmSeq().equals("0") || vo.getmSeq().equals("")) {
			// insert mode
		} else {
			// update mode
			model.addAttribute("memberItem", service.selectOne(memberDto));
		}
		
		return path + "MemberXdmForm";
	}
	
	/**
	 * 회원관리 - 입력한 데이터 수정하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberXdmUpdt")
	public String memberXdmUpdt(MemberDto memberDto) {
		service.update(memberDto);	

		return "redirect:MemberXdmList";
	}
	
	/**
	 * 회원관리 - 데이터 삭제하기
	 * @return redirect: 데이터 삭제 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberXdmDele")
	public String memberXdmDele(MemberDto memberDto) {
		service.delete(memberDto);	

		return "redirect:MemberXdmList";
	}
	
	/**
	 * 회원관리 - 데이터 삭제 옵션 세팅(update 이용)
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberXdmUele")
	public String memberXdmUele(MemberDto memberDto) {
		service.uelete(memberDto);	

		return "redirect:MemberXdmList";
	}

}
