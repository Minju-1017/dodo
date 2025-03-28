package com.dodo.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/xdm/")
public class IndexController {
	
	private String path = "xdm/";
	
	@RequestMapping(value = "index")	
	public String index(HttpSession httpSession) {
		if (httpSession.getAttribute("sessSeqXdm") == null) {
			return "xdm/member/MemberXdmSignIn";
		}
										
		return path + "index";
	}
}
