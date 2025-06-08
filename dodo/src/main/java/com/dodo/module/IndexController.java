package com.dodo.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dodo.module.game.GameDto;
import com.dodo.module.game.GameService;
import com.dodo.module.game.GameVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {
	
	private String path_admin = "xdm/";
	private String path_user = "usr/";
	
	@Autowired
	GameService gameService;
	
	@RequestMapping(value = "/xdm/index")	
	public String xdmIndex() {	
		return path_admin + "index";
	}
	
	@RequestMapping(value = "/usr/index")	
	public String usrIndex(Model model, @ModelAttribute("vo") GameVo vo, HttpSession httpSession) {	
		// addAttribute 하기 전에 미리 실행되야함
		vo.setRowNumToShow(10);
		vo.setParamsPaging(10);
		
		if (vo.getTotalRows() > 0) {
			// Top 10 List
			vo.setmSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
			List<GameDto> top10List = gameService.selectTop10List(vo);
			
			// 순위 리스트
			List<GameDto> dtoOrderList = gameService.selectGameOrderList();
			
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
				
		return path_user + "index";
	}
	
}
