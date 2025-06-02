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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dodo.Constants;
import com.dodo.common.mail.MailService;
import com.dodo.module.code.CodeService;
import com.dodo.module.codegroup.CodeGroupDto;
import com.dodo.module.codegroup.CodeGroupVo;
import com.dodo.module.file.FileService;
import com.dodo.module.game.GameDto;
import com.dodo.module.game.GameReviewDto;
import com.dodo.module.game.GameService;
import com.dodo.module.game.GameVo;

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
	GameService gameService;
	
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
	
	////////////////////////////Member Usr - 회원 관련////////////////////////////////////
	
	/**
	 * 회원가입 화면 이동 - User
	 * @return
	 */
	@RequestMapping(value = "MemberUsrSignUpForm")	
	public String memberUsrSignUpForm() {				
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
	 */
	@ResponseBody
	@RequestMapping(value = "MemberUsrInstProc")
	public Map<String, Object> memberUsrInstProc(MemberDto memberDto) {
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
	public String memberUsrSignIn() {				
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
	public String memberUsrSignInForgotPwdForm() {	
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
	 */
	@ResponseBody
	@RequestMapping(value = "MemberUsrSignOutProc")	
	public Map<String, Object> memberUsrSignOutProc(HttpSession httpSession) {
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
	 */
	@ResponseBody // Ajax 코드는 무조건 써준다.
	@RequestMapping(value = "MemberUsrUpdt")
	public Map<String, Object> memberUsrUpdt(MemberDto memberDto, HttpSession httpSession) {
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
	public Map<String, Object> memberUsrUeleProc(MemberDto memberDto) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int successCnt = service.uelete(memberDto);	
		
		if (successCnt == 1) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}

		return returnMap;
	}
	
	////////////////////////////Member Usr - Wish////////////////////////////////////
	
	/**
	 * 회원 위시 리스트 - 전체 데이터 읽어오기(페이징 기능 들어감) - User
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "MemberWishUsrList")
	public String memberWishUsrList(Model model, @ModelAttribute("vo") MemberWishVo vo, HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// addAttribute 하기 전에 미리 실행되야함
		vo.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		vo.setParamsPaging(service.selectWishListCount(vo));

		if (vo.getTotalRows() > 0) {
			// Wish List
			List<MemberWishDto> wishDtoList = service.selectWishList(vo);
			
			// 순위 리스트
			List<GameDto> dtoOrderList = gameService.selectGameOrderList();
			
			// 순위 설정
			for (MemberWishDto dto : wishDtoList) {
				for (GameDto orderDto : dtoOrderList) {
					if (dto.getgSeq().equals(orderDto.getgSeq())) {
						dto.setGrOrder(orderDto.getGrOrder());
						break;
					}
				}
			}
						
			model.addAttribute("wishList", wishDtoList);
		}
		
		return path_user + "MemberWishUsrList";
	}
	
	/**
	 * 입력한 데이터 추가하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberWishUsrInst", method = RequestMethod.POST)
	public String memberWishUsrInst(MemberWishDto memberWishDto, 
			@RequestParam("redirectUrl") String redirectUrl, HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		service.insertWish(memberWishDto);
		
		return "redirect:" + redirectUrl;
	}
	
	/**
	 * 입력한 데이터 삭제하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 * @throws Exception 
	 */
	@RequestMapping(value = "MemberWishUsrDele", method = RequestMethod.POST)
	public String memberWishUsrDele(MemberWishDto memberWishDto, 
			@RequestParam("redirectUrl") String redirectUrl, HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		if (redirectUrl.equals("/usr/member/MemberWishUsrList")) {
			service.deleteWishBySeq(memberWishDto);
		} else {
			service.deleteWishByCondition(memberWishDto);
		}
		
		return "redirect:" + redirectUrl;
	}
	
	/**
	 * Ajax를 이용해 입력한 데이터 추가하기 - Admin
	 */
	@RequestMapping(value = "MemberWishUsrInstByGameInfo", method = RequestMethod.POST)
	public String memberWishUsrInstByGameInfo(MemberWishDto memberWishDto, HttpSession httpSession, 
			Model model, @ModelAttribute("vo") GameVo vo) {
		// Login 상태일 때만 가능
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// 위시 추가
		service.insertWish(memberWishDto);
		
		// Game List 검색 필터에 맞춰서 읽어오기
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(gameService.selectGameInfoListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			// Game List
			List<GameDto> gameDtoList = gameService.selectGameInfoList(vo);
			
			// 순위 리스트
			List<GameDto> dtoOrderList = gameService.selectGameOrderList();
			
			// 순위 설정
			for (GameDto dto : gameDtoList) {
				for (GameDto orderDto : dtoOrderList) {
					if (dto.getgSeq().equals(orderDto.getgSeq())) {
						dto.setGrOrder(orderDto.getGrOrder());
						break;
					}
				}
			}
			
			model.addAttribute("gameList", gameDtoList);
		}

		// Thymeleaf fragment만 리턴
	    return "usr/game/GameInfoUsrList :: #gameListRefresh";
	}
	
	/**
	 * Ajax를 이용해 입력한 데이터 추가하기 - Admin
	 */
	@RequestMapping(value = "MemberWishUsrDeleByGameInfo", method = RequestMethod.POST)
	public String memberWishUsrDeleByGameInfo(MemberWishDto memberWishDto, HttpSession httpSession, 
			Model model, @ModelAttribute("vo") GameVo vo) {
		// Login 상태일 때만 가능
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// 위시 추가
		service.deleteWishByCondition(memberWishDto);
		
		// Game List 검색 필터에 맞춰서 읽어오기
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(gameService.selectGameInfoListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			// Game List
			List<GameDto> gameDtoList = gameService.selectGameInfoList(vo);
			
			// 순위 리스트
			List<GameDto> dtoOrderList = gameService.selectGameOrderList();
			
			// 순위 설정
			for (GameDto dto : gameDtoList) {
				for (GameDto orderDto : dtoOrderList) {
					if (dto.getgSeq().equals(orderDto.getgSeq())) {
						dto.setGrOrder(orderDto.getGrOrder());
						break;
					}
				}
			}
			
			model.addAttribute("gameList", gameDtoList);
		}

		// Thymeleaf fragment만 리턴
	    return "usr/game/GameInfoUsrList :: #gameListRefresh";
	}
	
	/**
	 * 입력한 데이터 추가하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberWishUsrInstByGameDetail", method = RequestMethod.POST)
	public String memberWishUsrInstByGameDetail(MemberWishDto memberWishDto, 
			Model model, GameDto gameDto, HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// 위시 추가
		service.insertWish(memberWishDto);
		
		// 게임 정보, 순위 리스트
		GameDto orderDto = gameService.selectGameOrder(gameDto);
		GameDto dto = gameService.selectOne(gameDto);
		dto.setGrOrder(orderDto.getGrOrder());
		model.addAttribute("gameItem", dto);
		
		// Thymeleaf fragment만 리턴
	    return "usr/game/GameUsrDetail :: #wishholdRefresh";
	}
	
	/**
	 * 입력한 데이터 추가하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberWishUsrDeleByGameDetail", method = RequestMethod.POST)
	public String memberWishUsrDeleByGameDetail(MemberWishDto memberWishDto, 
			Model model, GameDto gameDto, HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// 위시 추가
		service.deleteWishByCondition(memberWishDto);
		
		// 게임 정보, 순위 리스트
		GameDto orderDto = gameService.selectGameOrder(gameDto);
		GameDto dto = gameService.selectOne(gameDto);
		dto.setGrOrder(orderDto.getGrOrder());
		model.addAttribute("gameItem", dto);
		
		// Thymeleaf fragment만 리턴
	    return "usr/game/GameUsrDetail :: #wishholdRefresh";
	}
	
	/**
	 * 입력한 데이터 추가하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberWishUsrInstByGameDetailRelation", method = RequestMethod.POST)
	public String memberWishUsrInstByGameDetailRelation(MemberWishDto memberWishDto, 
			Model model, GameDto gameDto, HttpSession httpSession,
			@RequestParam("rgSeq") String rgSeq) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// 위시 추가
		memberWishDto.setgSeq(rgSeq);
		service.insertWish(memberWishDto);
		
		// 연관 게임 정보
		model.addAttribute("gameRelationList", gameService.selectGameRelationList(gameDto));
		
		// Thymeleaf fragment만 리턴
	    return "usr/game/GameUsrDetail :: #relationRefresh";
	}
	
	/**
	 * 입력한 데이터 추가하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberWishUsrDeleByGameDetailRelation", method = RequestMethod.POST)
	public String memberWishUsrDeleByGameDetailRelation(MemberWishDto memberWishDto, 
			Model model, GameDto gameDto, HttpSession httpSession,
			@RequestParam("rgSeq") String rgSeq) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// 위시 추가
		memberWishDto.setgSeq(rgSeq);
		service.deleteWishByCondition(memberWishDto);
		
		// 연관 게임 정보
		model.addAttribute("gameRelationList", gameService.selectGameRelationList(gameDto));
		
		// Thymeleaf fragment만 리턴
	    return "usr/game/GameUsrDetail :: #relationRefresh";
	}
	
	/**
	 * Ajax를 통한 여러건 데이터 삭제
	 * @param seqList
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "MemberWishListUsrDeleProc")
	public Map<String, Object> memberWishListUsrDeleProc(
			@RequestParam(value="chbox") List<String> seqList) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (seqList == null || (seqList != null && seqList.size() == 0)) {
			returnMap.put("rt", "fail");
		} else {
			int successCnt = service.listDeleteWish(seqList);
			
			if (successCnt > 0) {
				returnMap.put("rt", "success");
			} else {
				returnMap.put("rt", "fail");
			}
		}

		return returnMap;
	}
	
	////////////////////////////Member Usr - Hold////////////////////////////////////
	///
	/**
	 * 회원 위시 리스트 - 전체 데이터 읽어오기(페이징 기능 들어감) - User
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "MemberHoldUsrList")
	public String memberHoldUsrList(Model model, @ModelAttribute("vo") MemberHoldVo vo, HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// addAttribute 하기 전에 미리 실행되야함
		vo.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		vo.setParamsPaging(service.selectHoldListCount(vo));

		if (vo.getTotalRows() > 0) {
			// Hold List
			List<MemberHoldDto> holdDtoList = service.selectHoldList(vo);
			
			// 순위 리스트
			List<GameDto> dtoOrderList = gameService.selectGameOrderList();
			
			// 순위 설정
			for (MemberHoldDto dto : holdDtoList) {
				for (GameDto orderDto : dtoOrderList) {
					if (dto.getgSeq().equals(orderDto.getgSeq())) {
						dto.setGrOrder(orderDto.getGrOrder());
						break;
					}
				}
			}
						
			model.addAttribute("holdList", holdDtoList);
		}
		
		return path_user + "MemberHoldUsrList";
	}
	
	/**
	 * 데이터 추가/수정 폼
	 * @return
	 */
	@RequestMapping(value = "MemberHoldUsrForm")
	public String memberHoldUsrForm(@ModelAttribute("vo") MemberHoldVo vo, Model model, MemberHoldDto memberHoldDto) {
		if (vo.getMhSeq().equals("0") || vo.getMhSeq().equals("")) {
			// insert mode
		} else {
			// update mode
			model.addAttribute("holdItem", service.selectHoldOne(vo));
		}
		
		return path_user + "MemberHoldUsrForm";
	}
	
	/**
	 * Ajax를 통한 게임 검색 - User
	 * @param memberDto
	 * @return
	 * @throws Exception
	 */
	@ResponseBody // Ajax 코드는 무조건 써준다.
	@RequestMapping(value = "MemberHoleUsrSearchProc")
	public Map<String, Object> memberHoleUsrSearchProc(GameDto gameDto, HttpSession httpSession) {
		Map<String, Object> returnMap = new HashMap<String, Object>();		
		GameDto gDto = gameService.selectOneByName(gameDto); // MyBatis에서 디비 검색 후 결과값이 없으면 NULL이 떨어짐
		
		if (gDto == null) { 
			returnMap.put("rt", "fail");
		} else {
			if (httpSession.getAttribute("sessSeqUsr") == null) {
				returnMap.put("rt", "fail_login");
			} else {
				MemberHoldDto mhDto = new MemberHoldDto();
				mhDto.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
				mhDto.setGame_gSeq(gDto.getgSeq());
				
				int holdCount = service.insertHoldCheck(mhDto); // 내 등록 게임 리스트에 있는지 체크
				
				if (holdCount == 0) {
					returnMap.put("rt", gDto.getgSeq());
				} else {
					returnMap.put("rt", "fail_hold");
				}
			}		
		}
		
		return returnMap;
	}
	
	/**
	 * 입력한 데이터 추가하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberHoldUsrInst", method = RequestMethod.POST)
	public String memberHoldUsrInst(MemberHoldDto memberHoldDto, 
			@RequestParam("redirectUrl") String redirectUrl, HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		memberHoldDto.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		service.insertHold(memberHoldDto);
		
		return "redirect:" + redirectUrl;
	}
	
	/**
	 * 입력한 데이터 수정하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberHoldUsrUpdt")
	public String memberHoldUsrUpdt(MemberHoldDto memberHoldDto) {
		service.updateHold(memberHoldDto);

		return "redirect:MemberHoldUsrList";
	}
	
	/**
	 * 데이터 삭제하기
	 * @return redirect: 데이터 삭제 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberHoldUsrDele", method = RequestMethod.POST)
	public String memberHoldUsrDele(MemberHoldDto memberHoldDto,
			@RequestParam("redirectUrl") String redirectUrl) {
		
		if (redirectUrl.equals("/usr/member/MemberHoldUsrList")) {
			service.deleteHoldBySeq(memberHoldDto);
		} else {
			service.deleteHoldByCondition(memberHoldDto);
		}

		return "redirect:" + redirectUrl;
	}
	
	/**
	 * Ajax를 이용해 입력한 데이터 추가하기 - Admin
	 */
	@RequestMapping(value = "MemberHoldUsrInstByGameInfo", method = RequestMethod.POST)
	public String memberHoldUsrInstByGameInfo(MemberHoldDto memberHoldDto, HttpSession httpSession, 
			Model model, @ModelAttribute("vo") GameVo vo) {
		// Login 상태일 때만 가능
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// 보유게임 추가
		service.insertHold(memberHoldDto);
		
		// Game List 검색 필터에 맞춰서 읽어오기
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(gameService.selectGameInfoListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			// Game List
			List<GameDto> gameDtoList = gameService.selectGameInfoList(vo);
			
			// 순위 리스트
			List<GameDto> dtoOrderList = gameService.selectGameOrderList();
			
			// 순위 설정
			for (GameDto dto : gameDtoList) {
				for (GameDto orderDto : dtoOrderList) {
					if (dto.getgSeq().equals(orderDto.getgSeq())) {
						dto.setGrOrder(orderDto.getGrOrder());
						break;
					}
				}
			}
			
			model.addAttribute("gameList", gameDtoList);
		}

		// Thymeleaf fragment만 리턴
	    return "usr/game/GameInfoUsrList :: #gameListRefresh";
	}
	
	/**
	 * Ajax를 이용해 입력한 데이터 추가하기 - Admin
	 */
	@RequestMapping(value = "MemberHoldUsrDeleByGameInfo", method = RequestMethod.POST)
	public String memberHoldUsrDeleByGameInfo(MemberHoldDto memberHoldDto, HttpSession httpSession, 
			Model model, @ModelAttribute("vo") GameVo vo) {
		// Login 상태일 때만 가능
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// 보유게임 추가
		service.deleteHoldByCondition(memberHoldDto);
		
		// Game List 검색 필터에 맞춰서 읽어오기
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(gameService.selectGameInfoListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			// Game List
			List<GameDto> gameDtoList = gameService.selectGameInfoList(vo);
			
			// 순위 리스트
			List<GameDto> dtoOrderList = gameService.selectGameOrderList();
			
			// 순위 설정
			for (GameDto dto : gameDtoList) {
				for (GameDto orderDto : dtoOrderList) {
					if (dto.getgSeq().equals(orderDto.getgSeq())) {
						dto.setGrOrder(orderDto.getGrOrder());
						break;
					}
				}
			}
			
			model.addAttribute("gameList", gameDtoList);
		}

		// Thymeleaf fragment만 리턴
	    return "usr/game/GameInfoUsrList :: #gameListRefresh";
	}
	
	/**
	 * 입력한 데이터 추가하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberHoldUsrInstByGameDetail", method = RequestMethod.POST)
	public String memberHoldUsrInstByGameDetail(MemberHoldDto memberHoldDto, 
			Model model, GameDto gameDto, HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// 보유게임 추가
		service.insertHold(memberHoldDto);
		
		// 게임 정보, 순위 리스트
		GameDto orderDto = gameService.selectGameOrder(gameDto);
		GameDto dto = gameService.selectOne(gameDto);
		dto.setGrOrder(orderDto.getGrOrder());
		model.addAttribute("gameItem", dto);
		
		// Thymeleaf fragment만 리턴
	    return "usr/game/GameUsrDetail :: #wishholdRefresh";
	}
	
	/**
	 * 입력한 데이터 추가하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberHoldUsrDeleByGameDetail", method = RequestMethod.POST)
	public String memberHoldUsrDeleByGameDetail(MemberHoldDto memberHoldDto, 
			Model model, GameDto gameDto, HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// 보유게임 추가
		service.deleteHoldByCondition(memberHoldDto);
		
		// 게임 정보, 순위 리스트
		GameDto orderDto = gameService.selectGameOrder(gameDto);
		GameDto dto = gameService.selectOne(gameDto);
		dto.setGrOrder(orderDto.getGrOrder());
		model.addAttribute("gameItem", dto);
		
		// Thymeleaf fragment만 리턴
	    return "usr/game/GameUsrDetail :: #wishholdRefresh";
	}
	
	/**
	 * 입력한 데이터 추가하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberHoldUsrInstByGameDetailRelation", method = RequestMethod.POST)
	public String memberHoldUsrInstByGameDetailRelation(MemberHoldDto memberHoldDto, 
			Model model, GameDto gameDto, HttpSession httpSession,
			@RequestParam("rgSeq") String rgSeq) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// 보유게임 추가
		memberHoldDto.setgSeq(rgSeq);
		service.insertHold(memberHoldDto);
		
		// 연관 게임 정보
		model.addAttribute("gameRelationList", gameService.selectGameRelationList(gameDto));
		
		// Thymeleaf fragment만 리턴
	    return "usr/game/GameUsrDetail :: #relationRefresh";
	}
	
	/**
	 * 입력한 데이터 추가하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "MemberHoldUsrDeleByGameDetailRelation", method = RequestMethod.POST)
	public String memberHoldUsrDeleByGameDetailRelation(MemberHoldDto memberHoldDto, 
			Model model, GameDto gameDto, HttpSession httpSession,
			@RequestParam("rgSeq") String rgSeq) {
		if (httpSession.getAttribute("sessSeqUsr") == null) {
			usrSignOut(httpSession);
			return "redirect:MemberUsrSignIn";
		}
		
		// 보유게임 추가
		memberHoldDto.setgSeq(rgSeq);
		service.deleteHoldByCondition(memberHoldDto);
		
		// 연관 게임 정보
		model.addAttribute("gameRelationList", gameService.selectGameRelationList(gameDto));
		
		// Thymeleaf fragment만 리턴
	    return "usr/game/GameUsrDetail :: #relationRefresh";
	}
	
	
	
	
	
	
	/**
	 * Ajax를 통한 여러건 데이터 삭제
	 * @param seqList
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "MemberHoldListUsrDeleProc")
	public Map<String, Object> memberHoldListUsrDeleProc(
			@RequestParam(value="chbox") List<String> seqList) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (seqList == null || (seqList != null && seqList.size() == 0)) {
			returnMap.put("rt", "fail");
		} else {
			int successCnt = service.listDeleteHold(seqList);
			
			if (successCnt > 0) {
				returnMap.put("rt", "success");
			} else {
				returnMap.put("rt", "fail");
			}
		}

		return returnMap;
	}
	
	////////////////////////////Xdm////////////////////////////////////

	/**
	 * 로그인 화면 이동 - Admin
	 * @return
	 */
	@RequestMapping(value = "MemberXdmSignIn")	
	public String memberXdmSignIn() {				
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
	 */
	@ResponseBody
	@RequestMapping(value = "MemberXdmSignOutProc")	
	public Map<String, Object> memberXdmSignOutProc(HttpSession httpSession) {
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
	public String memberXdmList(Model model, @ModelAttribute("vo") MemberVo vo) {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectListCount(vo));

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
	public String memberXdmForm(@ModelAttribute("vo") MemberVo vo, Model model, MemberDto memberDto) {
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
	 */
	@ResponseBody
	@RequestMapping(value = "MemberListXdmDeleProc")
	public Map<String, Object> memberListXdmDeleProc(@RequestParam(value="chbox") List<String> seqList) {
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
	 */
	@ResponseBody
	@RequestMapping(value = "MemberListXdmUeleProc")
	public Map<String, Object> memberListXdmUeleProc(@RequestParam(value="chbox") List<String> seqList) {
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
