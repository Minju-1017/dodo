package com.dodo.module.game;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface GameDao {

	public int selectOneCount(GameVo vo);
	public List<GameDto> selectList(GameVo vo); 
	public List<GameDto> selectTop10List(GameVo vo);
	public List<GameDto> selectListWithoutPaging(); 
	public GameDto selectOne(GameDto gameDto);
	public int insert(GameDto gameDto);
	public int update(GameDto gameDto);
	public int delete(GameDto gameDto);
	public int uelete(GameDto gameDto);
	public int listDelete(List<String> seqList);
	public int listUelete(List<String> seqList);
	
}
