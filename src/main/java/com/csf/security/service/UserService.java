/**
 * 
 */
package com.csf.security.service;

import com.csf.security.domain.SignUpRequestDomain;
import com.csf.security.domain.UserDomain;
import com.csf.security.entity.Oauth2UserEntity;
import com.csf.security.entity.UserInfo;

/**
 * @author mba0051
 *
 */
public interface UserService {

	Oauth2UserEntity findByUserName(String name);

	UserInfo save(SignUpRequestDomain signUpRequest)  throws Exception;

	UserDomain findById(String userId);
}
