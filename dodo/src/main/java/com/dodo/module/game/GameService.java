package com.dodo.module.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
	
	@Autowired
	GameDao dao;
	
	public int selectOneCount(GameVo vo) {
		return dao.selectOneCount(vo);
	}
	
	public List<GameDto> selectList(GameVo vo) {
		return dao.selectList(vo);
	}
	
	public List<GameDto> selectListWithoutPaging() {
		return dao.selectListWithoutPaging();
	}
	
	public List<GameDto> selectTop10List(GameVo vo) {
		return dao.selectTop10List(vo);
	}
	
	public List<GameDto> selectOrderList(GameDto gameDto) {
		return dao.selectOrderList(gameDto);
	}
	
	public GameDto selectOne(GameDto gameDto) {
		return dao.selectOne(gameDto);
	}
	
	public int insert(GameDto gameDto) {
		return dao.insert(gameDto);
	}
	
	public int update(GameDto gameDto) {
		return dao.update(gameDto);
	}
	
	public int delete(GameDto gameDto) {
		return dao.delete(gameDto);
	}
	
	public int uelete(GameDto gameDto) {
		return dao.uelete(gameDto);
	}
	
	public int listDelete(List<String> seqList) {
		return dao.listDelete(seqList);
	}
	
	public int listUelete(List<String> seqList) {
		return dao.listUelete(seqList);
	}
	
	public int selectReviewOneCount(GameVo vo) {
		return dao.selectReviewOneCount(vo);
	}
	
	public List<GameReviewDto> selectReviewList(GameVo vo) {
		return dao.selectReviewList(vo);
	}
	
	public GameDto selectGameDetailReviewDistribution(GameDto gameDto) {
		return dao.selectGameDetailReviewDistribution(gameDto);
	}
	
	public List<GameReviewDto> selectGameDetailReviewList(GameDto gameDto) {
		return dao.selectGameDetailReviewList(gameDto);
	}
	
	public int insertReview(GameReviewDto gameReviewDto) {
		return dao.insertReview(gameReviewDto);
	}
	
}
