package com.dodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class) // Security Fliter 암호화
public class DodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DodoApplication.class, args);
	}

}
