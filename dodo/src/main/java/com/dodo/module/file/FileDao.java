package com.dodo.module.file;

import org.springframework.stereotype.Repository;

@Repository
public interface FileDao {

	public int selectListCount(FileDto fileDto);
	public FileDto selectOne(FileDto fileDto);
	public int insertFile(FileDto fileDto);
	public int updateFile(FileDto fileDto);
}
