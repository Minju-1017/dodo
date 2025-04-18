package com.dodo.common;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

@Component
public class DoDoUtil {
	
	private final static DecimalFormat DF_NUMBER_COMMA = new DecimalFormat("#,###");
	
	public static String formatNumberComma(String number) {
		return DF_NUMBER_COMMA.format(Integer.parseInt(number));
	}
	
	public static String formatNumberComma(int number) {
		return DF_NUMBER_COMMA.format(number);
	}
	
	public static String formatPhone(String number) {
		if (number == null) return "";
		
		if (number.length() == 8) {
		  return number.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
		} else if (number.length() == 12) {
		  return number.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
		} else {
			return number.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
		}
	}
	
}
