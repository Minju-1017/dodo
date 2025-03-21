package com.dodo.module;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class Constants {
	
	public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	// Member에 사용하는 코드 그룹
	public final static int MEMBER_CODE_GROUP_SEQ_GENDER = 1; // mGenderCd
	
	// Game에 사용하는 코드 그룹
	public final static int GAME_CODE_GROUP_SEQ_OFFICIAL = 1; // gOfficialCd

}
