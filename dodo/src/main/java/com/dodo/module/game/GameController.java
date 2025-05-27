package com.dodo.module.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodo.module.file.FileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value={"/xdm/game/", "/usr/game/"})
public class GameController {
	
	private String path_admin = "xdm/game/";
	private String path_user = "usr/game/";
	
	@Autowired
	GameService service;
	
	@Autowired
	FileService fileService;
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감 - Admin
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "GameXdmList")
	public String gameXdmList(Model model, @ModelAttribute("vo") GameVo vo, HttpSession httpSession) {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			vo.setmSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
			model.addAttribute("gameList", service.selectList(vo));
		}
		
		return path_admin + "GameXdmList";
	}
	
	/**
	 * 데이터 추가/수정 폼 - Admin
	 * @return
	 */
	@RequestMapping(value = "GameXdmForm")
	public String gameXdmForm(@ModelAttribute("vo") GameVo vo, Model model, 
			GameDto gameDto, HttpSession httpSession) {	
		if (vo.getgSeq().equals("0") || vo.getgSeq().equals("")) {
			// insert mode
		} else {
			// update mode
			gameDto.setrSeq(gameDto.getgSeq());
			model.addAttribute("gameSmallTnFile", fileService.selectOne(gameDto, "gameSmallTnFile"));
			model.addAttribute("gameLargeTnFile", fileService.selectOne(gameDto, "gameLargeTnFile"));
			
			gameDto.setmSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
			model.addAttribute("gameItem", service.selectOne(gameDto));
		}
		
		return path_admin + "GameXdmForm";
	}
	
	/**
	 * 입력한 데이터 추가하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 * @throws Exception 
	 */
	@RequestMapping(value = "GameXdmInst")
	public String gameXdmInst(GameDto gameDto) throws Exception {
		service.insert(gameDto);
		 
		// gSeq로 파일이름을 만들 것이므로 Game Table 먼저 insert 후 해야함 
		fileService.uploadFilesToS3(
				gameDto, 
				new String[]{"gameSmallTnFile", "gameLargeTnFile"}, 
				gameDto.getgSeq()
		);
		
		return "redirect:GameXdmList";
	}
	
	/**
	 * 입력한 데이터 수정하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 * @throws Exception 
	 */
	@RequestMapping(value = "GameXdmUpdt")
	public String gameXdmUpdt(GameDto gameDto) throws Exception {
		service.update(gameDto);	
		
		// gSeq로 파일이름을 만들 것이므로 Game Table 먼저 update 후 해야함 
		fileService.uploadFilesToS3(gameDto, 
				new String[]{"gameSmallTnFile", "gameLargeTnFile"}, 
				gameDto.getgSeq()
		);

		return "redirect:GameXdmList";
	}
	
	/**
	 * 데이터 삭제하기 - Admin
	 * @return redirect: 데이터 삭제 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "GameXdmDele")
	public String gameXdmDele(GameDto gameDto) {
		service.delete(gameDto);	

		return "redirect:GameXdmList";
	}
	
	/**
	 * 데이터 삭제 옵션 세팅 - update 이용 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "GameXdmUele")
	public String gameXdmUele(GameDto gameDto) {
		service.uelete(gameDto);	

		return "redirect:GameXdmList";
	}
	
	/**
	 * Ajax를 통한 여러건 데이터 삭제 - Admin
	 * @param seqList
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GameListXdmDeleProc")
	public Map<String, Object> gameListXdmDeleProc(@RequestParam(value="chbox") List<String> seqList) {
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
	 * Ajax를 통한 여러건 데이터 삭제 옵션 세팅 - update 이용 - Admin
	 * @param seqList
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GameListXdmUeleProc")
	public Map<String, Object> gameListXdmUeleProc(
			@RequestParam(value="chbox") List<String> seqList) {
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
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감 - Admin
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "GameReviewXdmList")
	public String gameReviewXdmList(Model model, @ModelAttribute("vo") GameVo vo) {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectReviewOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("gameReviewList", service.selectReviewList(vo));
		}
		
		return path_admin + "GameReviewXdmList";
	}
	
	/**
	 * 데이터 추가/수정 폼 - Admin
	 * @return
	 */
	@RequestMapping(value = "GameReviewXdmForm")
	public String gameReviewXdmForm(Model model, GameReviewDto gameReviewDto) {	
		model.addAttribute("gameReviewItem", service.selectReviewOne(gameReviewDto));
		
		return path_admin + "GameReviewXdmForm";
	}
	
	/**
	 * 입력한 데이터 수정하기 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "GameReviewXdmUpdt")
	public String gameReviewXdmUpdt(GameReviewDto gameReviewDto) {
		System.out.println("@@@@@@@@@@@@@@" + gameReviewDto.getGrSeq());
		service.updateReview(gameReviewDto);

		return "redirect:GameReviewXdmList";
	}
	
	/**
	 * 데이터 삭제하기 - Admin
	 * @return redirect: 데이터 삭제 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "GameReviewXdmDele")
	public String gameReviewXdmDele(GameReviewDto gameReviewDto) {
		service.deleteReview(gameReviewDto);	

		return "redirect:GameReviewXdmList";
	}
	
	/**
	 * 데이터 삭제 옵션 세팅 - update 이용 - Admin
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "GameReviewXdmUele")
	public String gameReviewXdmUele(GameReviewDto gameReviewDto) {
		service.ueleteReview(gameReviewDto);	

		return "redirect:GameReviewXdmList";
	}
	
	/**
	 * Ajax를 통한 여러건 데이터 삭제 - Admin
	 * @param seqList
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GameReviewListXdmDeleProc")
	public Map<String, Object> gameReviewListXdmDeleProc(@RequestParam(value="chbox") List<String> seqList) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (seqList == null || (seqList != null && seqList.size() == 0)) {
			returnMap.put("rt", "fail");
		} else {
			int successCnt = service.listDeleteReview(seqList);
			
			if (successCnt > 0) {
				returnMap.put("rt", "success");
			} else {
				returnMap.put("rt", "fail");
			}
		}

		return returnMap;
	}
	
	/**
	 * Ajax를 통한 여러건 데이터 삭제 옵션 세팅 - update 이용 - Admin
	 * @param seqList
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "GameReviewListXdmUeleProc")
	public Map<String, Object> gameReviewListXdmUeleProc(@RequestParam(value="chbox") List<String> seqList) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (seqList == null || (seqList != null && seqList.size() == 0)) {
			returnMap.put("rt", "fail");
		} else {
			int successCnt = service.listUeleteReview(seqList);
			
			if (successCnt > 0) {
				returnMap.put("rt", "success");
			} else {
				returnMap.put("rt", "fail");
			}
		}

		return returnMap;
	}
	
	/////////////////////////////////////////////////////////////////
	
	/**
	 * Top10 데이터 읽어오기 - 페이징 기능 들어감 - User
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "GameTop10UsrList")
	public String gameTop10UsrList(Model model, @ModelAttribute("vo") GameVo vo, HttpSession httpSession) {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setRowNumToShow(10);
		vo.setParamsPaging(10);
		
		if (vo.getTotalRows() > 0) {
			// Top 10 List
			vo.setmSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
			List<GameDto> top10List = service.selectTop10List(vo);
			
			// 순위 리스트
			List<GameDto> dtoOrderList = service.selectGameOrderList();
			
			// 순위 설정
			for (GameDto dto : top10List) {
				for (GameDto orderDto : dtoOrderList) {
					if (dto.getgSeq().equals(orderDto.getgSeq())) {
						dto.setGrOrder(orderDto.getGrOrder());
						break;
					}
				}
			}
						
			model.addAttribute("gameList", top10List);
		}
		
		return path_user + "GameTop10UsrList";
	}
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감 - User
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "GameInfoUsrList")
	public String gameInfoUsrList(Model model, @ModelAttribute("vo") GameVo vo, HttpSession httpSession) {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectGameInfoListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			// Game List
			vo.setmSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
			List<GameDto> gameDtoList = service.selectGameInfoList(vo);
			
			// 순위 리스트
			List<GameDto> dtoOrderList = service.selectGameOrderList();
			
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
		
		return path_user + "GameInfoUsrList";
	}
	
	/**
	 * Ajax를 이용해 필터 선택 부분 갱신 - User
	 * @param seqList
	 * @return
	 */
	@RequestMapping(value = "GameInfoSearchUsr", method = RequestMethod.POST)
	public String gameInfoSearchUsr(@ModelAttribute("vo") GameVo vo) {
		// Thymeleaf fragment만 리턴
	    return path_user + "GameInfoUsrList :: #setGameSearchValue";
	}
	
	/**
	 * Ajax를 이용해 제목 갱신 - User
	 * @param seqList
	 * @return
	 */
	@RequestMapping(value = "GameInfoSearchUsrTitle", method = RequestMethod.POST)
	public String gameInfoSearchUsrTitle(@ModelAttribute("vo") GameVo vo) {
		// Thymeleaf fragment만 리턴
	    return path_user + "GameInfoUsrList :: #titleRefresh";
	}
	
	/**
	 * Ajax로 검색 필터로 전체 데이터 읽어오기 - 페이징 기능 들어감 - User
	 * @param seqList
	 * @return
	 */
	@RequestMapping(value = "GameInfoSearchTotalUsrList", method = RequestMethod.POST)
	public String gameInfoSearchTotalUsrList(
			Model model, @ModelAttribute("vo") GameVo vo) {
		vo.setParamsPaging(service.selectGameInfoListCount(vo));

		// Thymeleaf fragment만 리턴
	    return path_user + "GameInfoUsrList :: #totalGameRefresh";
	}
	
	/**
	 * Ajax를 이용한 검색 필터로 전체 데이터 읽어오기 - 페이징 기능 들어감 - User
	 * @param seqList
	 * @return
	 */
	@RequestMapping(value = "GameInfoSearchUsrList", method = RequestMethod.POST)
	public String gameInfoSearchUsrList(
			Model model, @ModelAttribute("vo") GameVo vo, HttpSession httpSession) {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectGameInfoListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			// Game List
			vo.setmSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
			List<GameDto> gameDtoList = service.selectGameInfoList(vo);
			
			// 순위 리스트
			List<GameDto> dtoOrderList = service.selectGameOrderList();
			
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
	    return path_user + "GameInfoUsrList :: #gameListRefresh";
	}
	
	/**
	 * 데이터 상세보기 - User
	 * @return
	 */
	@RequestMapping(value = "GameUsrDetail")
	public String gameUsrDetail(Model model, GameDto gameDto, HttpSession httpSession) {
		// 순위 리스트
		gameDto.setmSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		GameDto orderDto = service.selectGameOrder(gameDto);
		GameDto dto1 = service.selectOne(gameDto);
		dto1.setGrOrder(orderDto.getGrOrder());
		
		// 썸네일
		gameDto.setrSeq(gameDto.getgSeq());
		model.addAttribute("gameLargeTnFile", fileService.selectOne(gameDto, "gameLargeTnFile"));
		
		// 게임 정보
		model.addAttribute("gameItem", dto1);
		
		// 리뷰 분포
		GameDto dto2 = service.selectGameDetailReviewDistribution(gameDto);
		if (dto2 == null) dto2 = new GameDto();
		model.addAttribute("gameDetailReviewDistribution", dto2);
		
		// 리뷰 리스트
		model.addAttribute("gameDetailReviewList", service.selectGameDetailReviewList(gameDto));
		
		// 연관 게임 리스트
		gameDto.setmSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		model.addAttribute("gameRelationList", service.selectGameRelationList(gameDto));
		
		return path_user + "GameUsrDetail";
	}
	
	/**
	 * 리뷰 더보기 - User
	 * @return
	 */
	@RequestMapping(value = "GameUsrDetailReviewMore", method = RequestMethod.POST)
	public String gameUsrDetailReviewMore(Model model, GameDto gameDto) {
    	model.addAttribute("gameDetailReviewList", service.selectGameDetailReviewList(gameDto));
    	
    	// Thymeleaf fragment만 리턴
	    return path_user + "GameUsrDetail :: #reviewList";
	}
	
	/**
	 * Ajax를 이용한 입력한 데이터 추가하기(리뷰) - User
	 * @param gameReviewDto
	 * @return
	 */
	@RequestMapping(value = "GameUsrDetailReviewInst", method = RequestMethod.POST)
	public String gameUsrDetailReviewInst(
			Model model, GameReviewDto gameReviewDto, GameDto gameDto, HttpSession httpSession) {
		int successCnt = service.insertReview(gameReviewDto);
			
		if (successCnt > 0) {
			// 순위 설정
			gameDto.setmSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
			GameDto orderDto = service.selectGameOrder(gameDto);
			GameDto dto = service.selectOne(gameDto);
			dto.setGrOrder(orderDto.getGrOrder());
			
			dto.setGrDtosSize(gameDto.getGrDtosSize());
			
			// 게임 정보
			model.addAttribute("gameItem", dto);
			
			// Thymeleaf fragment만 리턴
		    return path_user + "GameUsrDetail :: #gameInfoRefresh";
		} else {
		    return "";
		}
	}
	
	/**
	 * Ajax를 이용한 게임 디테일 화면 갱신(리뷰 분포) - User
	 * @param gameReviewDto
	 * @return
	 */
	@RequestMapping(value = "GameUsrDetailReviewDistributionRefresh", method = RequestMethod.POST)
	public String gameUsrDetailReviewDistributionRefresh(Model model, GameDto gameDto, HttpSession httpSession) {
		// 게임 정보
		gameDto.setmSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		model.addAttribute("gameItem", service.selectOne(gameDto));
		
		// 리뷰 분포
		GameDto dto = service.selectGameDetailReviewDistribution(gameDto);
		if (dto == null) dto = new GameDto();
		model.addAttribute("gameDetailReviewDistribution", dto);
		
		// Thymeleaf fragment만 리턴
	    return path_user + "GameUsrDetail :: #reviewDistributionRefresh";
	}
	
	/**
	 * Ajax를 이용한 입력한 데이터 추가 후 화면 갱신3(리뷰) - User
	 * @param gameReviewDto
	 * @return
	 */
	@RequestMapping(value = "GameUsrDetailReviewListRefresh", method = RequestMethod.POST)
	public String gameUsrDetailReviewListRefresh(Model model, GameDto gameDto) {	
		// 리뷰 리스트
		model.addAttribute("gameDetailReviewList", service.selectGameDetailReviewList(gameDto));
		
		// Thymeleaf fragment만 리턴
	    return path_user + "GameUsrDetail :: #reviewList";
	}

}
