package com.dodo.module.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
	
	@Autowired
	GameDao dao;
	
	public int selectListCount(GameVo vo) {
		return dao.selectListCount(vo);
	}
	
	public List<GameDto> selectList(GameVo vo) {
		return dao.selectList(vo);
	}
	
	public int selectGameInfoListCount(GameVo vo) {
		return dao.selectGameInfoListCount(vo);
	}
	
	public List<GameDto> selectGameInfoList(GameVo vo) {
		return dao.selectGameInfoList(vo);
	}
	
	public List<GameDto> selectTop10List(GameVo vo) {
		return dao.selectTop10List(vo);
	}
	
	public List<GameDto> selectGameOrderList() {
		return dao.selectGameOrderList();
	}
	
	public GameDto selectGameOrder(GameDto gameDto) {
		return dao.selectGameOrder(gameDto);
	}
	
	public List<GameDto> selectGameRelationList(GameDto gameDto) {
		return dao.selectGameRelationList(gameDto);
	}
	
	public GameDto selectOne(GameDto gameDto) {
		return dao.selectOne(gameDto);
	}
	
	public GameDto selectOneByName(GameDto gameDto) {
		return dao.selectOneByName(gameDto);
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
	
	public GameReviewDto selectReviewOne(GameReviewDto gameReviewDto) {
		return dao.selectReviewOne(gameReviewDto);
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
	
	public int updateReview(GameReviewDto gameReviewDto) {
		return dao.updateReview(gameReviewDto);
	}
	
	public int deleteReview(GameReviewDto gameReviewDto) {
		return dao.deleteReview(gameReviewDto);
	}
	
	public int ueleteReview(GameReviewDto gameReviewDto) {
		return dao.ueleteReview(gameReviewDto);
	}
	
	public int listDeleteReview(List<String> seqList) {
		return dao.listDeleteReview(seqList);
	}
	
	public int listUeleteReview(List<String> seqList) {
		return dao.listUeleteReview(seqList);
	}
	
}
