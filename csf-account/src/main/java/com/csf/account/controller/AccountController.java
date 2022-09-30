package com.csf.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csf.account.entity.MsgUser;
import com.csf.account.service.AccountService;
import com.csf.common.domain.LoginRequestDomain;
import com.csf.common.domain.ResponseDataAPI;

@RestController
@CrossOrigin(origins = "*")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDataAPI> login(@RequestBody LoginRequestDomain domain) throws Exception {
		ResponseDataAPI response = new ResponseDataAPI();
		try {
			response = accountService.checkLogin(domain);
		} catch (Exception e) {
			throw new Exception("Login and password invalid.");
		}
		return ResponseEntity.ok(response);
	}

	/**
	 * Test method. Use in API Gateway architecture.
	 * 
	 * @return
	 */
	@GetMapping(value = "/test")
	public ResponseEntity<ResponseDataAPI> testGetMethod(Authentication auth){
		String userId = String.valueOf(auth.getPrincipal());
		return ResponseEntity.ok(ResponseDataAPI.builder().data("Test GET method - User: " + userId).success(true).build());
	}
	@PostMapping(value = "/test")
	public ResponseEntity<ResponseDataAPI> testPostMethod(Authentication auth){
		String userId = String.valueOf(auth.getPrincipal());
		return ResponseEntity.ok(ResponseDataAPI.builder().data("Test POST method - User: " + userId).success(true).build());
	}

	/**
	 * Test method in Message Broker
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/members")
    public ResponseEntity<List<MsgUser>> getAll() {
        List<MsgUser> all = accountService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<MsgUser> register(@RequestBody MsgUser input) {
		MsgUser result = accountService.registerUser(input);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
