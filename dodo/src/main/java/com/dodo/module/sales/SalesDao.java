package com.dodo.module.sales;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface SalesDao {
	
	public int selectListCount(SalesVo vo);
	public List<SalesDto> selectList(SalesVo vo); 
	public SalesDto selectOne(SalesDto salesDto);
	public int plusHit(SalesDto salesDto);
	public int insertCheck(SalesDto salesDto);
	public int insert(SalesDto salesDto);
	public int update(SalesDto salesDto);
	public int delete(SalesDto salesDto);
}
