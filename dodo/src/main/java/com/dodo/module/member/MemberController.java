package com.dodo.module.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodo.Constants;
import com.dodo.module.code.CodeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value={"/xdm/member/", "/usr/member/"})
public class MemberController {

	private String path_xdm = "xdm/member/";
	private String path_user = "usr/member/";
	
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
		return path_xdm + "MemberXdmSignIn";
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
		
		MemberDto mDto = service.selectSignInMember(memberDto); // MyBatis에서 디비 검색 후 결과값이 없으면 NULL이 떨어짐
		
		if (mDto == null) { 
			httpSession.setAttribute("sessSeqXdm", null);
			returnMap.put("rt", "fail_none");
		} else {
			if (mDto.getmGradeCd() != Constants.MEMBER_GRADE_CODE_ADMIN) {
				returnMap.put("rt", "fail_member");
				mDto = null;
			} else {
				httpSession.setMaxInactiveInterval(Constants.SESSION_MINUTE_ADMIN);
				httpSession.setAttribute("sessSeqXdm", mDto.getmSeq());
				httpSession.setAttribute("sessIdXdm", mDto.getmId());
				httpSession.setAttribute("sessNameXdm", mDto.getmName());
				httpSession.setAttribute("sessGradeXdm", 
						CodeService.selectOneCachedCode(String.valueOf(mDto.getmGradeCd())));
				
				returnMap.put("rt", "success");
			}
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
		
		httpSession.setAttribute("sessSeqXdm", null);
		httpSession.setAttribute("sessIdXdm", null);
		httpSession.setAttribute("sessNameXdm", null);
		httpSession.setAttribute("sessGradeXdm", null);
		
		returnMap.put("rt", "success");
		
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
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectOneCount(vo));

		if (vo.getTotalRows() > 0) {
			model.addAttribute("memberList", service.selectList(vo));
		}
		
		return path_xdm + "MemberXdmList";
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
		
		return path_xdm + "MemberXdmForm";
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
