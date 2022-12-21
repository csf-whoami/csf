/**
 * 
 */
package com.csf.security.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.csf.common.constant.CommonConstants;
import com.csf.common.domain.LoginRequestDomain;
import com.csf.common.domain.ResponseDataAPI;
import com.csf.common.domain.SignUpRequestDomain;
import com.csf.common.domain.UserDomain;
import com.csf.common.utilities.StringUtils;
import com.csf.security.entity.Oauth2UserEntity;
import com.csf.security.entity.RoleEntity;
import com.csf.security.entity.UserInfo;
import com.csf.security.entity.UserRoleEntity;
import com.csf.security.repository.Oauth2UserRepository;
import com.csf.security.repository.RolesRepository;
import com.csf.security.repository.UserRoleRepository;
import com.csf.security.service.UserService;

/**
 * @author mba0051
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private Oauth2UserRepository repository;
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	@Override
	public Oauth2UserEntity findByUserName(String username) {
		return repository.findByUserName(username);
	}

	public UserInfo save(SignUpRequestDomain signUpRequest) throws Exception {
		// Creating user's account
		Oauth2UserEntity user = new Oauth2UserEntity();
		user.setId(StringUtils.generateUUID());
		user.setUserName(signUpRequest.getUsername());
		user.setUserPassword(signUpRequest.getPassword());
		user = repository.save(user);
		if (user == null) {
			throw new Exception("Save account not success");
		}

		// Save role
		RoleEntity role = rolesRepository.findByName(CommonConstants.UserRolesConstant.USER.getValue());
		if (role == null) {
			throw new Exception("Can not found user role.");
		}

		// Save User role.
		UserRoleEntity userRole = new UserRoleEntity();
		userRole.setId(StringUtils.generateUUID());
		userRole.setUser(user);
		userRole.setRole(role);
		userRole = userRoleRepository.save(userRole);
		if (userRole == null) {
			throw new Exception("Can not set user role.");
		}

		// Convert to DTO.
		UserInfo dto = new UserInfo(user.getId(), user.getUserName(), "",
				Collections.singleton(new SimpleGrantedAuthority(role.getName())));
		return dto;
	}

	@Override
	public UserDomain findById(String userId) {
		Optional<Oauth2UserEntity> user = repository.findById(userId);
		if (!user.isPresent()) {
			return null;
		}
		
		UserDomain domain = new UserDomain();
		domain.setUserId(user.get().getId());
		domain.setUserName(user.get().getUserName());
		return domain;
	}

	@Override
	public ResponseDataAPI checkLogin(LoginRequestDomain domain) throws Exception {
		ResponseDataAPI response = new ResponseDataAPI();

		final Oauth2UserEntity appUser = findByUserName(domain.getUsername());

		if (appUser != null) {
			final String providedUserEmail = domain.getUsername();
			final String providedUserPassword = domain.getPassword();

			if (providedUserEmail.equalsIgnoreCase(appUser.getUserName())
					&& bCryptEncoder.matches(providedUserPassword, appUser.getUserPassword())) {

				Set<SimpleGrantedAuthority> list_authorities = new HashSet<>();
				appUser.getUserRoles().forEach(role -> {
					list_authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().getCode()));
				});

				Authentication authInfo = new UsernamePasswordAuthenticationToken(appUser.getUserName(), appUser.getUserPassword(), list_authorities);
				System.out.println("authInfo: " + authInfo);
			}
		}

		response.setSuccess(false);
		return response;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Come here.");
		return null;
	}

}
