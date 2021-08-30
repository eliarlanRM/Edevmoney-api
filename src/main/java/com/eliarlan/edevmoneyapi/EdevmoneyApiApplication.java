package com.eliarlan.edevmoneyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.eliarlan.edevmoneyapi.config.property.EdevmoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(EdevmoneyApiProperty.class)
public class EdevmoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdevmoneyApiApplication.class, args);
	}
}
