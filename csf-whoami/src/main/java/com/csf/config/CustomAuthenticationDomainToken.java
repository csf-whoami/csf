package com.csf.config;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

public class CustomAuthenticationDomainToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	@Getter
	private final String networkId;

    /**
     * @param principal
     * @param credentials
     * @param networkId
     * @desctiption Custom authentication clinic token constructor
     * @author tuan
     */
    public CustomAuthenticationDomainToken(Object principal, Object credentials, String networkId) {
        super(principal, credentials);
        this.networkId = networkId;
    }

    /**
     * @param principal
     * @param credentials
     * @param authorities
     * @param networkId
     * @desctiption Custom authentication clinic token constructor
     * @author tuan
     */
    public CustomAuthenticationDomainToken(Object principal, Object credentials,
            Collection<? extends GrantedAuthority> authorities, String networkId) {
        super(principal, credentials, authorities);
        this.networkId = networkId;
    }
}
