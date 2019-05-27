package com.example.polls.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParameterConfig {
	@Value("${spring.sendgrid.api-key}")
	private String sendGridApiKey;
	
	@Value("${email.from}")
	private String emailFrom;
	
	@Value("${email.subject}")
	private String emailSubject;
	
	@Value("${verification.interval}")
	private long verificationInterval;

	public String getSendGridApiKey() {
		return sendGridApiKey;
	}

	public void setSendGridApiKey(String sendGridApiKey) {
		this.sendGridApiKey = sendGridApiKey;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public long getVerificationInterval() {
		return verificationInterval;
	}

	public void setVerificationInterval(long verificationInterval) {
		this.verificationInterval = verificationInterval;
	}	
}
