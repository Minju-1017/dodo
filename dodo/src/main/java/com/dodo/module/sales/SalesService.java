package com.dodo.module.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.module.member.MemberDto;

@Service
public class SalesService {
	
	@Autowired
	SalesDao dao;
	
	public int salesInstCheck(SalesDto salesDto) {
		return dao.salesInstCheck(salesDto);
	}

}
