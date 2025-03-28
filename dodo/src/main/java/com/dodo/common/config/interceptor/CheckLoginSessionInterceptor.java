package com.dodo.common.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dodo.Constants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckLoginSessionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		// 관리자용
		if(request.getRequestURI().contains(Constants.ABBREVIATION_ADMIN)) {
			if (request.getSession().getAttribute(Constants.SESSION_SEQ_NAME_ADMIN) == null) {
				response.sendRedirect(Constants.URL_LOGIN_FORM_ADMIN);
		        return false;
			}
		}
		
		// 사용자용
		if(request.getRequestURI().contains(Constants.ABBREVIATION_USER)) {
			if (request.getSession().getAttribute(Constants.SESSION_SEQ_NAME_USER) == null) {
				response.sendRedirect(Constants.URL_LOGIN_FORM_USER);
				return false;
			}
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
