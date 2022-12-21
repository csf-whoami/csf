/**
 * 
 */
package com.csf.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.csf.common.domain.LoginRequestDomain;
import com.csf.common.domain.ResponseDataAPI;
import com.csf.common.domain.SignUpRequestDomain;
import com.csf.common.domain.UserDomain;
import com.csf.security.entity.Oauth2UserEntity;
import com.csf.security.entity.UserInfo;

/**
 * @author mba0051
 *
 */
public interface UserService extends UserDetailsService {

	Oauth2UserEntity findByUserName(String name);

	UserInfo save(SignUpRequestDomain signUpRequest)  throws Exception;

	UserDomain findById(String userId);

	ResponseDataAPI checkLogin(LoginRequestDomain domain) throws Exception;
}
