package com.dodo;

import org.springframework.stereotype.Component;

@Component
public class Constants {
	
	// AWS Bucket
	public static String AWS_BUCKET = "dodomimi-bucket";
	
	// Url Path
	public static String ABBREVIATION_ADMIN = "xdm";
	public static String ABBREVIATION_USER = "usr";
	
	// Login Form URL
	public static String URL_LOGIN_FORM_ADMIN = "/xdm/member/MemberXdmSignIn";
	public static String URL_LOGIN_FORM_USER = "/usr/member/MemberUsrSignIn";
	
	// Login User Seq
	public static String SESSION_SEQ_NAME_ADMIN = "sessSeqXdm";
	public static String SESSION_SEQ_NAME_USER = "sessSeqUsr";
	
	// Login Session Time
	public static int SESSION_MINUTE_ADMIN = 60 * 30; // 30분
	public static int SESSION_MINUTE_USER = 60 * 30; // 30분
	
	// Member에 사용하는 코드 그룹
	public final static int MEMBER_CODE_GROUP_SEQ_GENDER = 1; // mGenderCd
	public final static int MEMBER_CODE_GROUP_SEQ_GRADE = 35; // mGenderCd
	
	// Game에 사용하는 코드 그룹
	public final static int GAME_CODE_GROUP_SEQ_OFFICIAL = 2; // gOfficialCd
	public final static int GAME_CODE_GROUP_SEQ_CATEGORY = 3; // gCategoryCd
	
	// Sales에 사용하는 코드 그룹
	public final static int SALES_CODE_GROUP_SEQ_STATE = 4; // msStateCd
	
	// 판매상태(msStateCd)
	public final static int SALES_CODE_ON_SALES = 20;
	public final static int SALES_CODE_SALES_COMPLETED = 22;
	
	// Sales Order에 사용하는 코드 그룹
	public final static int SALES_ORDER_CODE_GROUP_SEQ_DELI_STATE = 39; // msoDeliStateCd
	public final static int SALES_ORDER_CODE_GROUP_SEQ_DELI_COMP = 40; // msoDeliCompCd
	
	// 배송상태(msoDeliStateCd)
	public final static int SALES_CODE_DELI_READY = 52;
	public final static int SALES_CODE_DELI_IN = 53;
	public final static int SALES_CODE_DELI_COMPLETED = 54;
	
	// Member 등급
	public final static int MEMBER_GRADE_CODE_ADMIN = 43;
	public final static int MEMBER_GRADE_CODE_MEMBER= 44;

}
