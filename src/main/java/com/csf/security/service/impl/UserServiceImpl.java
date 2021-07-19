/**
 * 
 */
package com.csf.security.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.csf.base.utilities.StringUtils;
import com.csf.security.constant.CommonConstants;
import com.csf.security.domain.SignUpRequestDomain;
import com.csf.security.domain.UserDomain;
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
	Oauth2UserRepository repository;
	@Autowired
	RolesRepository rolesRepository;
	@Autowired
	UserRoleRepository userRoleRepository;

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

}
