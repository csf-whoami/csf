package com.csf.security.custom;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.csf.security.entity.Oauth2UserEntity;
import com.csf.security.entity.UserInfo;
import com.csf.security.service.UserService;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final Oauth2UserEntity userEntity = service.findByUserName(username);

		if (userEntity != null) {

			Set<SimpleGrantedAuthority> list_authorities = new HashSet<>();
			userEntity.getUserRoles().forEach(role -> {
				list_authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().getCode()));
			});

			UserInfo info = new UserInfo(userEntity.getId(), userEntity.getUserName(), passwordNoEncoding(userEntity), list_authorities);
			return info;
		}
		return null;
	}

	public UserDetails loadUserByType(String username, String type) throws UsernameNotFoundException {

		final Oauth2UserEntity userEntity = service.findByUserName(username);

		if (userEntity != null) {

			Set<SimpleGrantedAuthority> list_authorities = new HashSet<>();
			userEntity.getUserRoles().forEach(role -> {
				list_authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().getCode()));
			});

			UserInfo info = new UserInfo(userEntity.getId(), userEntity.getUserName(), passwordNoEncoding(userEntity), list_authorities);
			return info;
		}
		return null;
	}

	private String passwordNoEncoding(Oauth2UserEntity appUser) {
		// you can use one of bcrypt/noop/pbkdf2/scrypt/sha256
		// more:
		// https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding
		System.out.println("===== Go to passwordNoEncoding method. =====");
		return "{bcrypt}" + appUser.getUserPassword();
	}

	
}
