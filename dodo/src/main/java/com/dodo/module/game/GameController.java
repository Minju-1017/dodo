package com.dodo.module.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/xdm/game/")
public class GameController {
	
	private String path = "xdm/game/";
	
	@Autowired
	GameService service;
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "GameXdmList")
	public String gameXdmList(Model model, GameVo vo) throws Exception {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectOneCount());
		
		model.addAttribute("gameList", service.selectList(vo));
		model.addAttribute("vo", vo);
		
		return path + "GameXdmList";
	}
	
	/**
	 * 조건에 맞는 데이터 1줄만 읽어오기
	 * @param model
	 * @param codeListDto html에서 호출되는 파라메터와 일치하는 값이 있다면, 자동으로 바인딩 된다.
	 * @return
	 */
	@RequestMapping(value = "GameXdmItem")
	public String gameXdmItem(Model model, GameDto gameDto) {
		model.addAttribute("gameItem", service.selectOne(gameDto));
		
		return path + "GameXdmItem";
	}
	
	/**
	 * 데이터 입력 폼
	 * @return
	 */
	@RequestMapping(value = "GameXdmForm")
	public String gameXdmForm() {
		return path + "GameXdmForm";
	}
	
	/**
	 * 입력한 데이터 추가하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "GameXdmInst")
	public String gameXdmInst(GameDto gameDto) {
		service.insert(gameDto);
		
		return "redirect:GameXdmList";
	}
	
	/**
	 * 데이터 수정 폼
	 * 데이터 1개 읽어와서 화면에 보여주기
	 * @return
	 */
	@RequestMapping(value = "GameXdmMfom")
	public String gameXdmMfom(Model model, GameDto gameDto) {		
		model.addAttribute("gameItem", service.selectOne(gameDto));
		
		return path + "GameXdmMfom";
	}
	
	/**
	 * 입력한 데이터 수정하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "GameXdmUpdt")
	public String gameXdmUpdt(GameDto gameDto) {
		service.update(gameDto);	

		return "redirect:GameXdmList";
	}
	
	/**
	 * 데이터 삭제하기
	 * @return redirect: 데이터 삭제 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "GameXdmDele")
	public String gameXdmDele(GameDto gameDto) {
		service.delete(gameDto);	

		return "redirect:GameXdmList";
	}
	
	/**
	 * 데이터 삭제 옵션 세팅 - update 이용
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "GameXdmUele")
	public String gameXdmUele(GameDto gameDto) {
		service.uelete(gameDto);	

		return "redirect:GameXdmList";
	}

}
