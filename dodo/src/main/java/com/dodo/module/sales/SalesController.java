package com.dodo.module.sales;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dodo.Constants;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value={"/xdm/sales/", "/usr/sales/"})
public class SalesController {
	
	private String path_admin = "xdm/sales/";
	private String path_user = "usr/sales/";
	
	@Autowired
	SalesService service;
	
	/**
	 * 전체 데이터 읽어오기 - 페이징 기능 들어감
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "SalesUsrList")
	public String salesUsrList(Model model, @ModelAttribute("vo") SalesVo vo) {
		// addAttribute 하기 전에 미리 실행되야함
		vo.setParamsPaging(service.selectListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("salesList", service.selectList(vo));
		}
		
		return path_user + "SalesUsrList";
	}
	
	/**
	 * 검색된 데이터 읽어오기 - 페이징 기능 들어감
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "SalesSearchUsrList")
	public String salesSearchUsrList(Model model, @ModelAttribute("vo") SalesVo vo, 
			@RequestParam("salesShValue") String salesShValue) {
		// addAttribute 하기 전에 미리 실행되야함(판매중인 게임만 검색)
		if (salesShValue != null && !salesShValue.equals("")) vo.setShValue(salesShValue);
		vo.setShStateCd(Constants.SALES_CODE_ON_SALE);
		vo.setShOption(1);
		vo.setParamsPaging(service.selectListCount(vo));
		
		if (vo.getTotalRows() > 0) {
			model.addAttribute("salesList", service.selectList(vo));
		}
		
		return path_user + "SalesUsrList";
	}
	
	/**
	 * 데이터 보기
	 * @return
	 */
	@RequestMapping(value = "SalesUsrDetail")
	public String salesUsrDetail(Model model, SalesDto salesDto, HttpSession httpSession) {
		// 판매자가 아닌 경우 조회수 증가
		if (!String.valueOf(httpSession.getAttribute("sessSeqUsr")).equals(salesDto.getMember_mSeq())) {
			service.plusHit(salesDto);
		}
		
		model.addAttribute("salesItem", service.selectOne(salesDto));
		
		return path_user + "SalesUsrDetail";
	}
	
	/**
	 * Ajax를 통한 중고 거래 등록 체크
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "SalesUsrInstCheckProc")
	public Map<String, Object> salesUsrInstCheckProc(SalesDto salesDto, HttpSession httpSession) {	
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		salesDto.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		int cnt = service.insertCheck(salesDto);
		
		if (cnt == 0) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
	
	/**
	 * 데이터 추가 폼
	 * @return
	 */
	@RequestMapping(value = "SalesUsrForm")
	public String salesUsrForm(Model model, SalesDto salesDto, HttpSession httpSession) {
		if (salesDto.getMsSeq() == null || salesDto.getMsSeq().equals("0") || salesDto.getMsSeq().equals("")) {
			// insert mode
			salesDto.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
			model.addAttribute("salesItem", salesDto);
		} else {
			// update mode
			model.addAttribute("salesItem", service.selectOne(salesDto));
		}
		
		return path_user + "SalesUsrForm";
	}
	
	/**
	 * 데이터 추가
	 * @return
	 */
	@RequestMapping(value = "SalesUsrInst")
	public String salesUsrInst(SalesDto salesDto, HttpSession httpSession) {
		salesDto.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		service.insert(salesDto);
		
		return "redirect:SalesUsrList";
	}
	
	/**
	 * 입력한 데이터 수정하기
	 * @return redirect: 데이터 저장 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "SalesUsrUpdt")
	public String salesUsrUpdt(SalesDto salesDto) {
		service.update(salesDto);	

		return "redirect:SalesUsrList";
	}
	
	/**
	 * 데이터 삭제하기
	 * @return redirect: 데이터 삭제 후 돌아갈 주소(List)
	 */
	@RequestMapping(value = "SalesUsrDele")
	public String salesUsrDele(SalesDto salesDto) {
		service.delete(salesDto);	

		return "redirect:SalesUsrList";
	}

}
