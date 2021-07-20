package com.csf.whoami.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RefreshTokenDTO {
	@JsonProperty("access_token")
	private String token;
	@JsonProperty("refresh_token")
	private String refreshToken;
}
