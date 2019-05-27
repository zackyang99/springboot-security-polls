package com.example.polls.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
	@Autowired
	ParameterConfig pConfig;
	
	@Bean(name="gmailPropsSSL")
	public Properties gmailPropsSSL() {
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", "true");
		props.put("username", "");
		props.put("password", "");
		
		return props;
	}
	
	@Bean(name="gmailPropsTLS")
	public Properties gmailPropsTLS() {
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.debug", "true");
		props.put("username", "");
		props.put("password", "");
		
		return props;
	}
}
