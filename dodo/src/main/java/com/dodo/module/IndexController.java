package com.dodo.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/xdm/")
public class IndexController {
	
	private String path = "xdm/";
	
	@RequestMapping(value = "index")	
	public String index() {
										
		return path + "index";
	}
}
