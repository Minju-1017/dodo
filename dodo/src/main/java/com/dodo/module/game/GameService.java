package com.dodo.module.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
	
	@Autowired
	GameDao dao;
	
	public int selectOneCount() {
		return dao.selectOneCount();
	}
	
	public List<GameDto> selectList(GameVo vo) {
		return dao.selectList(vo);
	}
	
	public List<GameDto> selectListWithoutPaging() {
		return dao.selectListWithoutPaging();
	}
	
	public GameDto selectOne(GameDto codeGroupDto) {
		return dao.selectOne(codeGroupDto);
	}
	
	public int insert(GameDto codeGroupDto) {
		return dao.insert(codeGroupDto);
	}
	
	public int update(GameDto codeGroupDto) {
		return dao.update(codeGroupDto);
	}
	
	public int delete(GameDto codeGroupDto) {
		return dao.delete(codeGroupDto);
	}
	
	public int uelete(GameDto codeGroupDto) {
		return dao.uelete(codeGroupDto);
	}
}
