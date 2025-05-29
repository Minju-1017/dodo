package com.dodo.module.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/xdm/sales/", "/usr/sales/"})
public class SalesController {
	
	private String path_admin = "xdm/sales/";
	private String path_user = "usr/sales/";
	
	@Autowired
	SalesService service;
	
	

}
