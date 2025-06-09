package com.dodo.module.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesService {
	
	@Autowired
	SalesDao dao;
	
	public int selectXdmListCount(SalesVo vo) {
		return dao.selectXdmListCount(vo);
	}
	
	public List<SalesDto> selectXdmList(SalesVo vo) {
		return dao.selectXdmList(vo);
	}
	
	public SalesDto selectXdmOne(SalesDto salesDto) {
		return dao.selectXdmOne(salesDto);
	}
	
	public int uelete(SalesDto salesDto) {
		return dao.uelete(salesDto);
	}
	
	public int listDeleteOrder(List<String> seqList) {
		return dao.listDeleteOrder(seqList);
	}
	
	public int listDelete(List<String> seqList) {
		return dao.listDelete(seqList);
	}
	
	public int listUelete(List<String> seqList) {
		return dao.listUelete(seqList);
	}
	
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
	
	public int insert20minCheck(SalesOrderDto salesOrderDto) {
		return dao.insert20minCheck(salesOrderDto);
	}
	
	public int insertOrderCheck(SalesOrderDto salesOrderDto) {
		return dao.insertOrderCheck(salesOrderDto);
	}
	
	public int orderCheck(SalesOrderDto salesOrderDto) {
		return dao.orderCheck(salesOrderDto);
	}
	
	public int orderCheckSuccessState(SalesOrderDto salesOrderDto) {
		return dao.orderCheckSuccessState(salesOrderDto);
	}
	
	public int insertOrder(SalesOrderDto salesOrderDto) {
		return dao.insertOrder(salesOrderDto);
	}
	
	public int deleteOrder(SalesOrderDto salesOrderDto) {
		return dao.deleteOrder(salesOrderDto);
	}
	
	public int updateOrderSuccess(SalesOrderDto salesOrderDto) {
		return dao.updateOrderSuccess(salesOrderDto);
	}
	
	public int updateOrderDeli(SalesDto salesDto) {
		return dao.updateOrderDeli(salesDto);
	}
	
	public int updateOrderDeliComplate(SalesDto salesDto) {
		return dao.updateOrderDeliComplate(salesDto);
	}
	
	public int selectMySalesListCount(SalesVo vo) {
		return dao.selectMySalesListCount(vo);
	}
	
	public List<SalesDto> selectMySalesList(SalesVo vo) {
		return dao.selectMySalesList(vo);
	}
	
	public int selectMyBuyListCount(SalesVo vo) {
		return dao.selectMyBuyListCount(vo);
	}
	
	public List<SalesDto> selectMyBuyList(SalesVo vo) {
		return dao.selectMyBuyList(vo);
	}

}
