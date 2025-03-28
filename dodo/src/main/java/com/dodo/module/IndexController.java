package com.dodo.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	private String path_admin = "xdm/";
	private String path_user = "usr/";
	
	@RequestMapping(value = "/xdm/index")	
	public String xdmIndex() {	
		return path_admin + "index";
	}
	
	@RequestMapping(value = "/usr/index")	
	public String usrIndex() {	
		return path_user + "index";
	}
}
