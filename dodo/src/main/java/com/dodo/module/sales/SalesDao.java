package com.dodo.module.sales;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface SalesDao {
	
	public int selectXdmListCount(SalesVo vo);
	public List<SalesDto> selectXdmList(SalesVo vo); 
	public SalesDto selectXdmOne(SalesDto salesDto);
	public int uelete(SalesDto salesDto);
	public int listDeleteOrder(List<String> seqList);
	public int listDelete(List<String> seqList);
	public int listUelete(List<String> seqList);
	
	public int selectListCount(SalesVo vo);
	public List<SalesDto> selectList(SalesVo vo); 
	public SalesDto selectOne(SalesDto salesDto);
	public int plusHit(SalesDto salesDto);
	public int insertCheck(SalesDto salesDto);
	public int insert(SalesDto salesDto);
	public int update(SalesDto salesDto);
	public int updateSuccess(SalesOrderDto salesOrderDto);
	public int delete(SalesDto salesDto);
	
	public SalesDto selectOrderOne(SalesDto salesDto);
	public int orderCheckByMsSeq(SalesDto salesDto);
	public int insert20minCheck(SalesOrderDto salesOrderDto);
	public int insertOrderCheck(SalesOrderDto salesOrderDto);
	public int orderCheck(SalesOrderDto salesOrderDto);
	public int orderCheckSuccessState(SalesOrderDto salesOrderDto);
	public int insertOrder(SalesOrderDto salesOrderDto);
	public int deleteOrder(SalesOrderDto salesOrderDto);
	public int updateOrderSuccess(SalesOrderDto salesOrderDto);
	public int updateOrderDeli(SalesDto salesDto);
	public int updateOrderDeliComplate(SalesDto salesDto);
	
	public int selectMySalesListCount(SalesVo vo);
	public List<SalesDto> selectMySalesList(SalesVo vo); 
	
	public int selectMyBuyListCount(SalesVo vo);
	public List<SalesDto> selectMyBuyList(SalesVo vo); 
}
