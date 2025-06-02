package com.dodo.module.sales;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value={"/xdm/sales/", "/usr/sales/"})
public class SalesController {
	
	private String path_admin = "xdm/sales/";
	private String path_user = "usr/sales/";
	
	@Autowired
	SalesService service;
	
	/**
	 * Ajax를 통한 중고 거래 등록 체크
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "SalesUsrInstCheckProc")
	public Map<String, Object> salesUsrInstCheckProc(SalesDto salesDto, HttpSession httpSession) {	
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		System.out.println("##################");
		
		salesDto.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		int cnt = service.salesInstCheck(salesDto);
		
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
	@RequestMapping(value = "SalesUsrInstForm")
	public String SalesUsrInstForm(Model model, SalesDto salesDto, HttpSession httpSession) {
		salesDto.setMember_mSeq(String.valueOf(httpSession.getAttribute("sessSeqUsr")));
		model.addAttribute("salesItem", salesDto);
		
		return path_user + "SalesUsrInstForm";
	}

}
