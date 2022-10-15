/**
 * 
 */
package com.csf.security.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author tuan
 *
 */
public class TokenDomain {

	@JsonProperty("access_token")
	private String token;
	@JsonProperty("refresh_token")
	private String refreshToken;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
