package com.csf.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.csf.account.client.AuthServiceFeignClient;
import com.csf.account.entity.MsgUser;
import com.csf.account.entity.UserDto;
import com.csf.account.entity.UserRegistrationDto;
import com.csf.account.kafka.producer.Sender;
import com.csf.account.repository.MsgUserRepository;
import com.csf.common.constant.AuthConstant;
import com.csf.common.domain.LoginRequestDomain;
import com.csf.common.domain.ResponseDataAPI;

@Service
public class AccountServiceImpl implements AccountService {

	// For Gateway
	@Autowired
	private AuthServiceFeignClient authServiceFeignClient;
	@Autowired
	private RestTemplate restTemplate;
	// For message broker
	@Autowired
	private MsgUserRepository msgUserRepository;
	@Autowired
	private Sender sender;

	@Value("${uaa-service.uri.home}")
	private String uaaServiceUrl;
	@Value("${uaa-service.uri.token}")
	private String uaaTokenUrl;
	@Value("${uaa-service.auth.client-id}")
	private String clientId;
	@Value("${uaa-service.auth.client-secret}")
	private String clientSecret;
	// For message broker.
	@Value("${spring.kafka.topic.userCreated}")
    private String USER_CREATED_TOPIC;

	private final String AUTH_USERNAME = "username";
	private final String AUTH_PASSWORD = "password";
	private final String AUTH_TYPE = "grant_type";
	private final String AUTH_CLIENT_ID = "client_id";
	private final String AUTH_CLIENT_SECRET = "client_secret";
	private final String AUTH_SCOPE = "scope";

	@Override
	public UserDto create(UserRegistrationDto user) {
		return authServiceFeignClient.createUser(user);
	}

	@Override
	public ResponseDataAPI checkLogin(LoginRequestDomain domain) throws Exception {

		ResponseDataAPI response = new ResponseDataAPI();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		StringBuilder internalUrl = new StringBuilder();
		internalUrl.append(uaaServiceUrl);
		internalUrl.append(uaaTokenUrl);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add(AUTH_USERNAME, domain.getUsername());
		map.add(AUTH_PASSWORD, domain.getPassword());
		map.add(AUTH_TYPE, AUTH_PASSWORD);
		map.add(AUTH_CLIENT_ID, clientId);
		map.add(AUTH_CLIENT_SECRET, clientSecret);
		map.add(AUTH_SCOPE, AuthConstant.SCOPE_READ);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		try {
			ResponseEntity<DefaultOAuth2AccessToken> authenticate = restTemplate.postForEntity(internalUrl.toString(),
					request, DefaultOAuth2AccessToken.class);
			if (authenticate.getStatusCode() == HttpStatus.OK) {
				response.setSuccess(true);
				response.setData(authenticate.getBody());
				return response;
			}
		} catch (HttpClientErrorException ex) {
			throw new Exception(ex.getResponseBodyAsString());
		}

		response.setSuccess(false);
		return response;
	}

	@Override
	public List<MsgUser> findAll() {
		return msgUserRepository.findAll();
	}

	/**
	 * Save user and send a notification to Message Broker.
	 */
	@Override
	public MsgUser registerUser(MsgUser input) {
		MsgUser createdUser = msgUserRepository.save(input);
        sender.send(USER_CREATED_TOPIC, createdUser);
        return createdUser;
	}
}
