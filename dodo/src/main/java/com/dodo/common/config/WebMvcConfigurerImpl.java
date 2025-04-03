package com.dodo.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dodo.common.config.interceptor.CheckLoginSessionInterceptor;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CheckLoginSessionInterceptor())
				.addPathPatterns("/*/*/*Xdm*", "/*/*/*Usr*", "/xdm/index")
				.excludePathPatterns(
					"/assets/**", // static/assets 폴더안 모든 것은 허용하겠다는 것
					"/xdm/member/MemberXdmSignIn",
					"/xdm/member/MemberXdmSignInProc",
					"/usr/index",
					"/usr/member/MemberUsrSignUpForm",
					"/usr/member/MemberUsrInstProc",
					"/usr/member/MemberUsrSignIn",
					"/usr/member/MemberUsrSignInProc",
					"/usr/member/MemberUsrSignInForgotPwdForm",
					"/usr/member/MemberUsrSignInForgotPwdProc"
		);
	}

}