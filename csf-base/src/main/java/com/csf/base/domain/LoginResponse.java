package com.csf.base.domain;

import com.csf.base.domain.response.AccountInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class LoginResponse {
	public AccountInfo user;
	@JsonProperty("access_token")
	private String token;
	@JsonProperty("refresh_token")
	private String refreshToken;
}
