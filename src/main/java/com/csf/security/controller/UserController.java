/**
 * Functions list:
 * 
 * - Signup
 * - View information
 * - Update infor
 * 
 * - Active account
 * - Inactive account
 * - Delete account forever. - Decider.
 */
package com.csf.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.csf.base.domain.ResponseDataAPI;
import com.csf.security.domain.SignUpRequestDomain;
import com.csf.security.domain.UserDomain;
import com.csf.security.entity.UserInfo;
import com.csf.security.service.UserService;

/**
 * @author mba0051
 *
 */
@RestController
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	@GetMapping("/me")
	@PreAuthorize("hasRole('USER')")
	public UserDomain getCurrentUser(Authentication auth) throws Exception {

		UserInfo userdto = (UserInfo) auth.getPrincipal();
		UserDomain user = userService.findById(userdto.getUserId());
		if (user == null) {
			throw new Exception("Can not found User.");
		}
		return user;
	}

	@PostMapping("/signup")
	public ResponseEntity<ResponseDataAPI> registerUser(@Valid @RequestBody SignUpRequestDomain signUpRequest)
			throws Exception {

		ResponseDataAPI res = new ResponseDataAPI();
		if (userService.findByUserName(signUpRequest.getUsername()) != null) {
			throw new Exception("User name exist.");
		}
		signUpRequest.setPassword(bCryptEncoder.encode(signUpRequest.getPassword()));
		userService.save(signUpRequest);

		res.setSuccess(true);
		res.setData(true);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
