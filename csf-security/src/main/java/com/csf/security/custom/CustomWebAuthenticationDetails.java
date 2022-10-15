package com.csf.security.custom;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = -6072713891329479729L;
	private String token;

	/**
	 * Records the remote address and will also set the session Id if a session
	 * already exists (it won't create one).
	 *
	 * @param request that the authentication request was received from
	 */
	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		this.token = request.getParameter("jdjCustomToken");
	}

	@Override
	public String toString() {
		return "CustomWebAuthenticationDetails{" + "token='" + token + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		CustomWebAuthenticationDetails that = (CustomWebAuthenticationDetails) o;
		return Objects.equals(token, that.token);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), token);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
