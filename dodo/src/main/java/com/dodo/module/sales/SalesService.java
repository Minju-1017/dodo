package com.dodo.module.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesService {
	
	@Autowired
	SalesDao dao;

}
