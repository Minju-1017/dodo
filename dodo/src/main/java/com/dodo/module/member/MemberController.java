package com.dodo.module.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodo.Constants;
import com.dodo.module.code.CodeDto;
import com.dodo.module.code.CodeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value={"/xdm/member/", "/usr/member/"})
public class MemberController {

	private String path_admin = "xdm/member/";
	private String path_user = "usr/member/";
	
	@Autowired
	MemberService service;
	
	@Autowired
	CodeService codeService;
	
	/**
	 * 회원가입 화면 이동 - User
	 * @return
	 */
	@RequestMapping(value = "MemberUsrSignUpForm")	
	public String memberUsrSignUpForm() throws Exception {				
		return path_user + "MemberUsrSignUpForm";
	}
	
	/**
	 * Ajax를 통한 회원가입 - User
	 * @param memberDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "MemberUsrInstProc")
	public Map<String, Object> memberUsrInstProc(MemberDto memberDto) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int cntId = service.insertCheckId(memberDto);
		
		if (cntId == 0) {
			int cntEmail = service.insertCheckEmail(memberDto);
			
			if (cntEmail == 0) {
				int cntSuccess = service.insert(memberDto);
				
				if (cntSuccess == 1) {
					returnMap.put("rt", "success");
				} else {
					returnMap.put("rt", "fail");
				}
			} else {
				returnMap.put("rt", "fail_email");
			}
		} else {
			returnMap.put("rt", "fail_id");
		}
		
		return returnMap;
	}
	
	/**
	 * 로그인 화면 이동 - User
	 * @return
	 */
	@RequestMapping(value = "MemberUsrSignIn")	
	public String memberUsrSignIn() throws Exception {				
		return path_user + "MemberUsrSignIn";
	}
	
	/**
	 * Ajax를 통한 로그인 처리 - User
	 * @param memberDto
	 * @return
	 * @throws Exception
	 */
	@ResponseBody // Ajax 코드는 무조건 써준다.
	@RequestMapping(value = "MemberUsrSignInProc")
	public Map<String, Object> memberUsrSignInProc(MemberDto memberDto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		MemberDto mDto = service.selectSignInMember(memberDto); // MyBatis에서 디비 검색 후 결과값이 없으면 NULL이 떨어짐
		
		if (mDto == null) { 
			httpSession.setAttribute("sessSeqUsr", null);
			returnMap.put("rt", "fail");
		} else {
			httpSession.setMaxInactiveInterval(Constants.SESSION_MINUTE_USER);
			httpSession.setAttribute("sessSeqUsr", mDto.getmSeq());
			httpSession.setAttribute("sessIdUsr", mDto.getmId());
			httpSession.setAttribute("sessNameUsr", mDto.getmName());
			httpSession.setAttribute("sessGradeUsr", 
					CodeService.selectOneCachedCode(String.valueOf(mDto.getmGradeCd())));
			
			returnMap.put("rt", "success");
		}
		
		return returnMap;
	}
	
	/**
	 * 비밀번호 재설정 화면 이동 - User
	 * @return
	 */
	@RequestMapping(value = "MemberUsrSignInForgotPwdForm")	
	public String memberUsrSignInForgotPwdForm() throws Exception {	
		return path_user + "MemberUsrSignInForgotPwdForm";
	}
	
	/**
	 * Ajax를 통한 비밀번호 재설정 처리 - User
	 * @param memberDto
	 * @return
	 * @throws Exception
	 */
	@ResponseBody // Ajax 코드는 무조건 써준다.
	@RequestMapping(value = "MemberUsrSignInForgotPwdProc")
	public Map<String, Object> memberUsrSignInForgotPwdProc(MemberDto memberDto) throws Exception {
		System.out.println("#################");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		int cntSuccess = service.updatePwd(memberDto);
		
		if (cntSuccess == 1) { 
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
	
	/**
	 * Ajax를 통한 로그아웃 처리
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "MemberUsrSignOutProc")	
	public Map<String, Object> memberUsrSignOutProc(HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		httpSession.setAttribute("sessSeqUsr", null);
		httpSession.setAttribute("sessIdUsr", null);
		httpSession.setAttribute("sessNameUsr", null);
		httpSession.setAttribute("sessGradeUsr", null);
		
		returnMap.put("rt", "success");
		
		return returnMap;
	}
	
	////////////////////////////////////////////////////////////////////
	
	/**
	 * 로그인 화면 이동 - Admin
	 * @return
	 */
	@RequestMapping(value = "MemberXdmSignIn")	
	public String memberXdmSignIn() throws Exception {				
		return path_admin + "MemberXdmSignIn";
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
		
		return path_admin + "MemberXdmList";
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
		
		return path_admin + "MemberXdmForm";
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
	
	/**
	 * Ajax를 통한 여러건 데이터 삭제
	 * @param seqList
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "MemberListXdmDeleProc")
	public Map<String, Object> memberListXdmDeleProc(
			@RequestParam(value="chbox") List<String> seqList) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (seqList == null || (seqList != null && seqList.size() == 0)) {
			returnMap.put("rt", "fail");
		} else {
			int successCnt = service.listDelete(seqList);
			
			if (successCnt > 0) {
				returnMap.put("rt", "success");
			} else {
				returnMap.put("rt", "fail");
			}
		}

		return returnMap;
	}
	
	/**
	 * Ajax를 통한 여러건 데이터 삭제 옵션 세팅 - update 이용
	 * @param seqList
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "MemberListXdmUeleProc")
	public Map<String, Object> memberListXdmUeleProc(
			@RequestParam(value="chbox") List<String> seqList) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (seqList == null || (seqList != null && seqList.size() == 0)) {
			returnMap.put("rt", "fail");
		} else {
			int successCnt = service.listUelete(seqList);
			
			if (successCnt > 0) {
				returnMap.put("rt", "success");
			} else {
				returnMap.put("rt", "fail");
			}
		}

		return returnMap;
	}

}
