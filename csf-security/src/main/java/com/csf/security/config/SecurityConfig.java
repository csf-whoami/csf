package com.csf.security.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.csf.security.custom.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * The constant PERMIT_AUTHENTICATION_URLS.
	 */
	public static List<String> PERMIT_AUTHENTICATION_URLS = //getAccessUrlFromDB();
			Arrays.asList("/auth/login", "/auth/register", "/accounts/**",
			"/security/key", "/auth/health-life/login", "/basic/common/**", "/swagger-ui.html", "/v2/api-docs",
			"/swagger-resources/**", "/webjars/springfox-swagger-ui/**", "/auth/guest", "/resources/templates",
			"/favicon.ico", "/auth/forgot-password", "/common/app/version");

	@Autowired
	private CustomUserDetailsService customeUserDetailsService;

	/**
	 * Encoder bcrypt password encoder.
	 *
	 * @return the bcrypt password encoder
	 */
	@Bean
	public BCryptPasswordEncoder bcryptEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
	      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//	      return bCryptPasswordEncoder;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder, PasswordEncoder passwordEncoder) throws Exception {
    builder.userDetailsService(customeUserDetailsService).passwordEncoder(passwordEncoder);
    }
	
//	@Autowired
//	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customeUserDetailsService).passwordEncoder(passwordEncoder());
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(PERMIT_AUTHENTICATION_URLS.toArray(new String[] {})).permitAll().anyRequest()
				.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}