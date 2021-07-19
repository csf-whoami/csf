package com.csf.security.custom;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.csf.security.entity.Oauth2UserEntity;
import com.csf.security.service.UserService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		if (authentication.getName() == null || authentication.getCredentials() == null) {
			return null;
		}

		if (authentication.getName().isEmpty() || authentication.getCredentials().toString().isEmpty()) {
			return null;
		}

		final Oauth2UserEntity appUser = userService.findByUserName(authentication.getName());

		if (appUser != null) {
			final String providedUserEmail = authentication.getName();
			final Object providedUserPassword = authentication.getCredentials();

			if (providedUserEmail.equalsIgnoreCase(appUser.getUserName())
					&& bCryptEncoder.encode(providedUserPassword.toString()).equals(appUser.getUserPassword())) {

				Set<SimpleGrantedAuthority> list_authorities = new HashSet<>();
				appUser.getUserRoles().forEach(role -> {
					list_authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().getCode()));
				});

				return new UsernamePasswordAuthenticationToken(appUser.getUserName(), appUser.getUserPassword(),
						list_authorities);
			}
		}

		throw new UsernameNotFoundException("Invalid username or password.");
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
