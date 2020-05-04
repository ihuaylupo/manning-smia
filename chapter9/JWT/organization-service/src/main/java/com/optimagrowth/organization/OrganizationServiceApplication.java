package com.optimagrowth.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@RefreshScope
@EnableResourceServer
public class OrganizationServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

}
