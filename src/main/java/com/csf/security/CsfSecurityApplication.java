package com.csf.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CsfSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsfSecurityApplication.class, args);
	}

	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder();
	}

	/**
	 * @description create bean for restTemplate
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
		return restTemplateBuilder().additionalInterceptors((httpRequest, bytes, clientHttpRequestExecution) -> {
			return clientHttpRequestExecution.execute(httpRequest, bytes);
		}).build();
	}
}
