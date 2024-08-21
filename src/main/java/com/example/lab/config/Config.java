package com.example.lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {
//	@Bean(name = "messageSource")
//	public MessageSource getMessageResource() {
//		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
//
//		messageResource.setBasename("i18n/messages");
//		messageResource.setDefaultEncoding("UTF-8");
//		return messageResource;
//	}
}
