package com.csf.whoami.business;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = { "/manager" }, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api
public class ManagerController {

//	@Value("${oauth2.client-id}")
//	private String CLIENT_ID;
//
//	@Autowired
//	private TokenStore tokenStore;
//
//	@ApiOperation(value = "Phương thức lấy danh sách tất cả các Token đang có trong hệ thống.")
//	@RequestMapping(method = RequestMethod.GET, path = "/token/list")
//	@ResponseStatus(HttpStatus.OK)
//	@Secured({ "ROLE_ADMIN" })
//	public List<String> findAllTokens() {
//		final Collection<OAuth2AccessToken> tokensByClientId = tokenStore.findTokensByClientId(CLIENT_ID);
//		return tokensByClientId.stream().map(token -> token.getValue()).collect(Collectors.toList());
//	}
}
