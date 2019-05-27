package com.example.polls.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class BeanConfig {
	@Autowired ParameterConfig pConfig;
	
	public SendGrid sendGrid() {
		return new SendGrid(pConfig.getSendGridApiKey());
	}
}
