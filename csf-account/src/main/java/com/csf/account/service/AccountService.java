package com.csf.account.service;

import java.util.List;

import com.csf.account.entity.MsgUser;
import com.csf.account.entity.UserDto;
import com.csf.account.entity.UserRegistrationDto;
import com.csf.common.domain.LoginRequestDomain;
import com.csf.common.domain.ResponseDataAPI;

public interface AccountService {

    /**
     * Invokes Auth Service user creation
     *
     * @param user
     * @return created account
     */
    UserDto create(UserRegistrationDto user);

    ResponseDataAPI checkLogin(LoginRequestDomain domain) throws Exception;

    /**
     * Find all user. \n
     * Case message broker.
     * 
     * @return
     */
	List<MsgUser> findAll();

	/**
	 * Save user in architect message broker.
	 * 
	 * @param input
	 * @return
	 */
	MsgUser registerUser(MsgUser input);
}
