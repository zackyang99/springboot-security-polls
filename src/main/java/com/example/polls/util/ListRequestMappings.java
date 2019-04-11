package com.example.polls.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class ListRequestMappings implements ApplicationListener {
	Logger logger = LoggerFactory.getLogger(ListRequestMappings.class);

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if (event instanceof ContextRefreshedEvent) {
            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
            logger.info("List of request mappings");
            applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods().entrySet().forEach(entry -> {
            	logger.info(entry.getKey().toString() + ": " + entry.getValue());
            });
        }
		
	}

}
