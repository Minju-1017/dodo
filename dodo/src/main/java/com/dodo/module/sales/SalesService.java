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
	
	public int updateSuccess(SalesOrderDto salesOrderDto) {
		return dao.updateSuccess(salesOrderDto);
	}
	
	public int delete(SalesDto salesDto) {
		return dao.delete(salesDto);
	}
	
	public SalesDto selectOrderOne(SalesDto salesDto) {
		return dao.selectOrderOne(salesDto);
	}
	
	public int orderCheckByMsSeq(SalesDto salesDto) {
		return dao.orderCheckByMsSeq(salesDto);
	}
	
	public int insertOrderCheck(SalesOrderDto salesOrderDto) {
		return dao.insertOrderCheck(salesOrderDto);
	}
	
	public int orderCheck(SalesOrderDto salesOrderDto) {
		return dao.orderCheck(salesOrderDto);
	}
	
	public int insertOrder(SalesOrderDto salesOrderDto) {
		return dao.insertOrder(salesOrderDto);
	}
	
	public int deleteOrderByMSSeq(SalesDto salesDto) {
		return dao.deleteOrderByMSSeq(salesDto);
	}
	
	public int deleteOrder(SalesOrderDto salesOrderDto) {
		return dao.deleteOrder(salesOrderDto);
	}
	
	public int updateOrderSuccess(SalesOrderDto salesOrderDto) {
		return dao.updateOrderSuccess(salesOrderDto);
	}
	
	public int updateOrderDeli(SalesOrderDto salesOrderDto) {
		return dao.updateOrderDeli(salesOrderDto);
	}
	
	public int selectMySalesListCount(SalesVo vo) {
		return dao.selectMySalesListCount(vo);
	}
	
	public List<SalesDto> selectMySalesList(SalesVo vo) {
		return dao.selectMySalesList(vo);
	}

}
