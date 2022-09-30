package com.csf.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;

/**
 * The @EnableOAuth2Sso will enable configuration for an OAuth2 client in a web
 * application that uses Spring Security and wants to use the Authorization Code
 * Grant from our auth-service and create a WebSecurityConfigurerAdapter with
 * all paths secured. <br/>
 * And the @EnableFeignClients scans for interfaces that declare they as feign
 * clients.
 * 
 * @author mba0051
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableOAuth2Sso
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CsfAccountApplication {
	public static void main(String[] args) {
		SpringApplication.run(CsfAccountApplication.class, args);
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
