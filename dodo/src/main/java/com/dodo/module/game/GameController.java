package com.dodo.module.game;

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
	public String gameXdmList(Model model, @ModelAttribute("vo") GameVo vo,
			HttpSession httpSession) throws Exception {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("gameList", service.selectList(vo));
		}
		
		return path_admin + "GameXdmList";
	}
	
	/**
	 * 데이터 추가/수정 폼 - Admin
	 * @return
	 */
	@RequestMapping(value = "GameXdmForm")
	public String gameXdmForm(@ModelAttribute("vo") GameVo vo,
			Model model, GameDto gameDto) throws Exception {	
		if (vo.getgSeq().equals("0") || vo.getgSeq().equals("")) {
			// insert mode
		} else {
			// update mode
			gameDto.setrSeq(gameDto.getgSeq());
			model.addAttribute("gameSmallTnFile", fileService.selectOne(gameDto, "gameSmallTnFile"));
			model.addAttribute("gameLargeTnFile", fileService.selectOne(gameDto, "gameLargeTnFile"));
			
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
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "GameListXdmDeleProc")
	public Map<String, Object> gameListXdmDeleProc(
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
	 * Ajax를 통한 여러건 데이터 삭제 옵션 세팅 - update 이용 - Admin
	 * @param seqList
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "GameListXdmUeleProc")
	public Map<String, Object> gameListXdmUeleProc(
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
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감 - Admin
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "GameReviewXdmList")
	public String gameReviewXdmList(Model model, @ModelAttribute("vo") GameVo vo,
			HttpSession httpSession) throws Exception {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectReviewOneCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("gameReviewList", service.selectReviewList(vo));
		}
		
		return path_admin + "GameReviewXdmList";
	}
	
	/////////////////////////////////////////////////////////////////
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감 - User
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "GameTop10UsrList")
	public String gameTop10UsrList(Model model, @ModelAttribute("vo") GameVo vo,
			HttpSession httpSession) throws Exception {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setRowNumToShow(10);
		vo.setParamsPaging(10);
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("gameList", service.selectTop10List(vo));
		}
		
		return path_user + "GameTop10UsrList";
	}
	
	/**
	 * 데이터 상세보기 - User
	 * @return
	 */
	@RequestMapping(value = "GameUsrDetail")
	public String gameUsrDetail(Model model, GameDto gameDto) throws Exception {	
		// 순위 리스트
		List<GameDto> dtoOrderList = service.selectOrderList(gameDto);
		GameDto dto = service.selectOne(gameDto);
		
		// 순위 설정
		for (GameDto orderDto : dtoOrderList) {
			if (orderDto.getgSeq().equals(dto.getgSeq())) {
				dto.setGrOrder(orderDto.getGrOrder());
				break;
			}
		}
		
		gameDto.setrSeq(gameDto.getgSeq());
		model.addAttribute("gameLargeTnFile", fileService.selectOne(gameDto, "gameLargeTnFile"));
		
		model.addAttribute("gameItem", dto);
		
		// 리뷰 리스트
		int reviewTotalCount = service.selectReviewTotalCount(dto);
		dto.setGrDtosTotalSize(reviewTotalCount);
		
		if (reviewTotalCount > 0) {
			model.addAttribute("gameDetailReviewList", service.selectGameDetailReviewList(dto));
		}
		
		return path_user + "GameUsrDetail";
	}

}
