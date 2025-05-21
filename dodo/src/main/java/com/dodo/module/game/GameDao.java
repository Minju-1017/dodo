package com.dodo.module.game;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface GameDao {

	public int selectListCount(GameVo vo);
	public List<GameDto> selectList(GameVo vo); 
	public int selectGameInfoListCount(GameVo vo);
	public List<GameDto> selectGameInfoList(GameVo vo);
	public List<GameDto> selectTop10List(GameVo vo);
	public List<GameDto> selectOrderList(GameDto gameDto);
	public List<GameDto> selectGameRelationList(GameDto gameDto);
	public GameDto selectOne(GameDto gameDto);
	public int insert(GameDto gameDto);
	public int update(GameDto gameDto);
	public int delete(GameDto gameDto);
	public int uelete(GameDto gameDto);
	public int listDelete(List<String> seqList);
	public int listUelete(List<String> seqList);
	
	public int selectReviewOneCount(GameVo vo);
	public List<GameReviewDto> selectReviewList(GameVo vo); 
	public GameDto selectGameDetailReviewDistribution(GameDto gameDto);
	public List<GameReviewDto> selectGameDetailReviewList(GameDto gameDto); 
	public int insertReview(GameReviewDto gameReviewDto);
	
}
