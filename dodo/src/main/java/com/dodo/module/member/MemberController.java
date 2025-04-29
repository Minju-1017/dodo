package com.dodo.module.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dodo.Constants;
import com.dodo.common.mail.MailService;
import com.dodo.module.code.CodeService;
import com.dodo.module.file.FileService;

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
	
	@Autowired
	MailService mailService;
	
	@Autowired
	FileService fileService;
	
	/**
	 * 로그인 세션 처리 - User
	 * @param httpSession
	 * @param memberDto
	 * @throws Exception
	 */
	private void usrSignIn(HttpSession httpSession, MemberDto memberDto) throws Exception {
		httpSession.setMaxInactiveInterval(Constants.SESSION_MINUTE_USER);
		httpSession.setAttribute("sessSeqUsr", memberDto.getmSeq());
		httpSession.setAttribute("sessIdUsr", memberDto.getmId());
		httpSession.setAttribute("sessNameUsr", memberDto.getmName());
		httpSession.setAttribute("sessGradeUsr", 
				CodeService.selectOneCachedCode(String.valueOf(memberDto.getmGradeCd())));
		httpSession.setAttribute("sessPfFileNameUsr", memberDto.getfPath());
	}
	
	/**
	 * 로그아웃 세션 처리 - User
	 * @param httpSession
	 */
	private void usrSignOut(HttpSession httpSession) {
		httpSession.setAttribute("sessSeqUsr", null);
		httpSession.setAttribute("sessIdUsr", null);
		httpSession.setAttribute("sessNameUsr", null);
		httpSession.setAttribute("sessGradeUsr", null);
		httpSession.setAttribute("sessPfFileNameUsr", null);
	}
	
	/**
	 * 로그인 세션 처리 - Xdm
	 * @param httpSession
	 * @param memberDto
	 * @throws Exception
	 */
	private void xdmSignIn(HttpSession httpSession, MemberDto memberDto) throws Exception {
		httpSession.setMaxInactiveInterval(Constants.SESSION_MINUTE_ADMIN);
		httpSession.setAttribute("sessSeqXdm", memberDto.getmSeq());
		httpSession.setAttribute("sessIdXdm", memberDto.getmId());
		httpSession.setAttribute("sessNameXdm", memberDto.getmName());
		httpSession.setAttribute("sessGradeXdm", 
				CodeService.selectOneCachedCode(String.valueOf(memberDto.getmGradeCd())));
		httpSession.setAttribute("sessPfFileNameXdm", memberDto.getfPath());
	}
	
	/**
	 * 로그아웃 세션 처리 - Xdm
	 * @param httpSession
	 */
	private void xdmSignOut(HttpSession httpSession) {
		httpSession.setAttribute("sessSeqXdm", null);
		httpSession.setAttribute("sessIdXdm", null);
		httpSession.setAttribute("sessNameXdm", null);
		httpSession.setAttribute("sessGradeXdm", null);
		httpSession.setAttribute("sessPfFileNameXdm", null);
	}
	
	/**
	 * 암호화
	 * @param str
	 * @param length
	 * @return
	 */
	public String encodeBcrypt(String str, int length) {
		return new BCryptPasswordEncoder(length).encode(str);
	}
	
	/**
	 * 암호화된 문자열 체크
	 * @param str
	 * @param length
	 * @return
	 */
	public boolean matchesBcrypt(String inputStr, String str, int length) {
		return new BCryptPasswordEncoder(length).matches(inputStr, str);
	}
	
	///////////////////////////////////////////////////////////////////
	
	/**
	 * 회원가입 화면 이동 - User
	 * @return
	 */
	@RequestMapping(value = "MemberUsrSignUpForm")	
	public String memberUsrSignUpForm() throws Exception {				
		return path_user + "MemberUsrSignUpForm";
	}
	
	/**
	 * Ajax를 통한 회원가입 Id 체크 - User
	 * @param memberDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "MemberUsrInstIdCheckProc")
	public Map<String, Object> memberUsrInstIdCheckProc(MemberDto memberDto) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int cntId = service.insertCheckId(memberDto);
		
		if (cntId == 0) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail_id");
		}
		
		return returnMap;
	}
	
	/**
	 * Ajax를 통한 회원가입 - User
	 * @param memberDto
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "MemberUsrInstProc")
	public Map<String, Object> memberUsrInstProc(MemberDto memberDto) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int cntId = service.insertCheckId(memberDto);
		
		if (cntId == 0) {
			memberDto.setmPwd(encodeBcrypt(memberDto.getmPwd(), 10)); // 패스워드 암호화
			int cntSuccess = service.insert(memberDto);
			
			if (cntSuccess == 1) {
				returnMap.put("rt", "success");
				
				// 가입 축하 메일 보내기(SMTP 이용) - 오래 걸리므로, 새로운 쓰레드에서 보낸다.
				new Thread() {
					public void run() {
						try {
							mailService.sendMailWelcome(memberDto);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
				
			} else {
				returnMap.put("rt", "fail");
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
			usrSignOut(httpSession);
			returnMap.put("rt", "fail");
			mDto = null;
		} else {
			if (matchesBcrypt(memberDto.getmPwd(), mDto.getmPwd(), 10)) {
				usrSignIn(httpSession, mDto);
				returnMap.put("rt", "success");
			} else {
				usrSignOut(httpSession);
				returnMap.put("rt", "fail");
				mDto = null;
			}
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
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		memberDto.setmPwd(encodeBcrypt(memberDto.getmPwd(), 10)); // 패스워드 암호화
		int cntSuccess = service.updateForgotPwd(memberDto);
		
		if (cntSuccess == 1) { 
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
	
	/**
	 * Ajax를 통한 로그아웃 처리 - User
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "MemberUsrSignOutProc")	
	public Map<String, Object> memberUsrSignOutProc(HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		usrSignOut(httpSession);
		returnMap.put("rt", "success");
		
		return returnMap;
	}
	
	/**
	 * 회원 기본 정보 수정 화면 - User
	 * @param model
	 * @param memberDto
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "MemberUsrAccountForm")
	public String memberUsrAccountForm(Model model, MemberDto memberDto, HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		memberDto.setmSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		memberDto.setrSeq(memberDto.getmSeq());
		
		model.addAttribute("memberPfFile", fileService.selectOne(memberDto, "memberPfFile"));
		model.addAttribute("memberItem", service.selectOne(memberDto));
		
		return path_user + "MemberUsrAccountForm";
	}
	
	/**
	 * Ajax를 통한 회원 기본 정보 수정 - User
	 * @param memberDto
	 * @param httpSession
	 * @return 
	 * @throws Exception
	 */
	@ResponseBody // Ajax 코드는 무조건 써준다.
	@RequestMapping(value = "MemberUsrUpdt")
	public Map<String, Object> memberUsrUpdt(MemberDto memberDto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int successCnt = service.update(memberDto);
		
		if (successCnt == 1) {
			if (httpSession.getAttribute("sessSeqUsr") != null
					 && String.valueOf(httpSession.getAttribute("sessSeqUsr")).equals(memberDto.getmSeq())) {
				httpSession.setAttribute("sessNameUsr", memberDto.getmName());
			}
			
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}

		return returnMap;
	}
	
	/**
	 * Ajax를 통한 회원 기본 정보 수정(프로필 사진) - User
	 * @param memberDto
	 * @param httpSession
	 * @return 
	 * @throws Exception
	 */
	@ResponseBody // Ajax 코드는 무조건 써준다.
	@RequestMapping(value = "MemberUsrPfUpdt")
	public Map<String, Object> memberUsrPfUpdt(
			@RequestParam("fUploadFiles") MultipartFile file,
			@RequestParam("mSeq") String mSeq,
			MemberDto memberDto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		memberDto.setmSeq(mSeq);
		memberDto.setfUploadFiles(new MultipartFile[]{file});
		
		fileService.uploadFilesToS3(
				memberDto, 
				new String[]{"memberPfFile"}, 
				memberDto.getmSeq()
		);
		
		if (httpSession.getAttribute("sessSeqUsr") != null
				 && String.valueOf(httpSession.getAttribute("sessSeqUsr")).equals(memberDto.getmSeq())
				 && memberDto.getfPath() != null
				 && !memberDto.getfPath().equals("")) {
			httpSession.setAttribute("sessPfFileNameUsr", memberDto.getfPath());
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
	
	/**
	 * 회원 비밀번호 수정 화면 - User
	 * @param model
	 * @param memberDto
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "MemberUsrAccountPwdForm")
	public String memberUsrAccountPwdForm(Model model, MemberDto memberDto, HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		return path_user + "MemberUsrAccountPwdForm";
	}
	
	/**
	 * Ajax를 통한 회원 비밀번호 수정 처리 - User
	 * @param memberDto
	 * @return
	 * @throws Exception
	 */
	@ResponseBody // Ajax 코드는 무조건 써준다.
	@RequestMapping(value = "MemberUsrPwdUpdtProc")
	public Map<String, Object> memberUsrPwdUpdtProc(
			MemberDto memberDto, @RequestParam(value="mPwdNew") String mPwdNew) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		if (mPwdNew == null || (mPwdNew != null && mPwdNew.equals(""))) {
			returnMap.put("rt", "fail");
		} else {
			MemberDto mDto = service.updatePwdCheck(memberDto);
			
			if (mDto != null) {
				if (matchesBcrypt(memberDto.getmPwd(), mDto.getmPwd(), 10)) {
					memberDto.setmPwd(encodeBcrypt(mPwdNew, 10)); // 패스워드 암호화
					int successCnt = service.updatePwd(memberDto);
					
					if (successCnt == 1) {
						returnMap.put("rt", "success");
					} else {
						returnMap.put("rt", "fail");
					}
				} else {
					returnMap.put("rt", "fail");
				}
			} else {
				returnMap.put("rt", "fail");
			}
		}

		return returnMap;
	}
	
	/**
	 * 회원 탈퇴 화면 - User
	 * @param model
	 * @param memberDto
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "MemberUsrWithdrawal")
	public String memberUsrWithdrawal(Model model, MemberDto memberDto, HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		return path_user + "MemberUsrWithdrawal";
	}
	
	/**
	 * Ajax를 통한 회원 탈퇴 처리 - User(update 이용)
	 * @param memberDto
	 * @param httpSession
	 * @return
	 */
	@ResponseBody // Ajax 코드는 무조건 써준다.
	@RequestMapping(value = "MemberUsrUeleProc")
	public Map<String, Object> memberUsrUeleProc(MemberDto memberDto, HttpSession httpSession) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int successCnt = service.uelete(memberDto);	
		
		if (successCnt == 1) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}

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
	 * Ajax를 통한 로그인 처리 - Admin
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
			returnMap.put("rt", "fail_none");
		} else {
			if (!matchesBcrypt(memberDto.getmPwd(), mDto.getmPwd(), 10)) {
				returnMap.put("rt", "fail_none");
				mDto = null;
			} else if (mDto.getmGradeCd() != Constants.MEMBER_GRADE_CODE_ADMIN) {
				returnMap.put("rt", "fail_member");
				mDto = null;
			} else {
				xdmSignIn(httpSession, mDto);
				returnMap.put("rt", "success");
			}
		}
		
		return returnMap;
	}
	
	/**
	 * Ajax를 통한 로그아웃 처리 - Admin
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "MemberXdmSignOutProc")	
	public Map<String, Object> memberXdmSignOutProc(HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		xdmSignOut(httpSession);
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
			memberDto.setrSeq(memberDto.getmSeq());
			model.addAttribute("memberPfFile", fileService.selectOne(memberDto, "memberPfFile"));
						
			model.addAttribute("memberItem", service.selectOne(memberDto));
		}
		
		return path_admin + "MemberXdmForm";
	}
	
	/**
	 * 회원관리 - 입력한 데이터 수정하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberXdmUpdt")
	public String memberXdmUpdt(MemberDto memberDto, HttpSession httpSession) throws Exception {
		int successCnt = service.update(memberDto);
		
		// gSeq로 파일이름을 만들 것이므로 Game Table 먼저 insert 후 해야함 
		fileService.uploadFilesToS3(
				memberDto, 
				new String[]{"memberPfFile"}, 
				memberDto.getmSeq()
		);
		
		if (successCnt == 1
				 && httpSession.getAttribute("sessSeqXdm") != null
				 && String.valueOf(httpSession.getAttribute("sessSeqXdm")).equals(memberDto.getmSeq())) {
			httpSession.setAttribute("sessNameXdm", memberDto.getmName());
			httpSession.setAttribute("sessGradeXdm", 
					CodeService.selectOneCachedCode(String.valueOf(memberDto.getmGradeCd())));
			httpSession.setAttribute("sessPfFileNameXdm", memberDto.getfPath());
		}

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
