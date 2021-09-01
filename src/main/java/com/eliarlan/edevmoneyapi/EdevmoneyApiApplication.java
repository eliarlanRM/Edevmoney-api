package com.eliarlan.edevmoneyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import com.eliarlan.edevmoneyapi.config.property.EdevmoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(EdevmoneyApiProperty.class)
public class EdevmoneyApiApplication {
	
	private static ApplicationContext APPLICATION_CONTEXT;
	
	public static void main(String[] args) {
		APPLICATION_CONTEXT = SpringApplication.run(EdevmoneyApiApplication.class, args);
	}
	
	public static <T> T getBean(Class<T> type){
		return APPLICATION_CONTEXT.getBean(type);
	}
}
