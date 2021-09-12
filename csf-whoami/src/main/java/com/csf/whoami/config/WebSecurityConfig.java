package com.csf.whoami.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    @Qualifier("userService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    /**
     * The constant PERMIT_AUTHENTICATION_URLS.
     */
    public static final List<String> PERMIT_AUTHENTICATION_URLS = Arrays
            .asList("/auth/login", "/auth/register", "/security/key", "/login",
                    "/basic/common/**",
                    "/swagger-ui.html", "/v2/api-docs", "/swagger-resources/**", "/webjars/springfox-swagger-ui/**",
                    "/resources/templates", "/favicon.ico",
                    "/common/app/version",
                    "/**");

    private static final List<String> REQUIRE_AUTHENTICATION_URLS = Arrays
            .asList("/main/**", "/me/**", "/menu", "/start", "/resetPassword", "*");

    private static final String LOGIN_PAGE = "/login";
    private static final String LOGOUT_PAGE = "/login?logout=true";
    private static final String HOME_PAGE = "/home";
    private static final String LOGIN_ERROR_PAGE = "/login?error=true";

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

    /**
     * @param http
     * @throws Exception
     * @description configure spring security
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
        .and().csrf().disable()
        .authorizeRequests()
        // require authentication.
//        .antMatchers(REQUIRE_AUTHENTICATION_URLS.toArray(new String[] {})).authenticated()
        // For all access
        .antMatchers(PERMIT_AUTHENTICATION_URLS.toArray(new String[] {})).permitAll()
        // Default login page
        .and()
//            .formLogin()
//            .loginPage(LOGIN_PAGE)
//            .defaultSuccessUrl(HOME_PAGE)
//            .failureUrl(LOGIN_ERROR_PAGE)
//            .permitAll()
//        // Default logout page.
//        .and()
//            .logout()
//            .logoutSuccessUrl(LOGOUT_PAGE)
//            .invalidateHttpSession(true)
//            .permitAll()
//        .and()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().sameOrigin();

    }

    /**
     * @return BCryptPasswordEncoder
     * @description create bean for BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
