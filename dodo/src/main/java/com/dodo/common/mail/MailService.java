package com.dodo.common.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.dodo.module.member.MemberDto;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	//	회원가입 축하 메일
    public void sendMailWelcome(MemberDto memberDto) throws Exception {
    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    	MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
    	
    	mimeMessageHelper.setTo(memberDto.getmEmail()); 
    	mimeMessageHelper.setSubject("Boardgame Mania 회원 가입 축하 메일");
    	mimeMessageHelper.setText("보드게임 매니아에 오신 것을 환영합니다!", true); 
    	
    	javaMailSender.send(mimeMessage);
    }
}
