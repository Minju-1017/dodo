package com.dodo.module.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesService {
	
	@Autowired
	SalesDao dao;
	
	public int selectListCount(SalesVo vo) {
		return dao.selectListCount(vo);
	}
	
	public List<SalesDto> selectList(SalesVo vo) {
		return dao.selectList(vo);
	}
	
	public SalesDto selectOne(SalesDto salesDto) {
		return dao.selectOne(salesDto);
	}
	
	public int plusHit(SalesDto salesDto) {
		return dao.plusHit(salesDto);
	}
	
	public int insertCheck(SalesDto salesDto) {
		return dao.insertCheck(salesDto);
	}
	
	public int insert(SalesDto salesDto) {
		return dao.insert(salesDto);
	}
	
	public int update(SalesDto salesDto) {
		return dao.update(salesDto);
	}
	
	public int delete(SalesDto salesDto) {
		return dao.delete(salesDto);
	}

}
