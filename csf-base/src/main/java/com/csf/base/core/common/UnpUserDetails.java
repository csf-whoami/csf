package com.csf.base.core.common;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class UnpUserDetails extends User {

	private static final long serialVersionUID = 1L;

	public UnpUserDetails(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Object usersVO)
			throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		setUsersVO(usersVO);
		
	}
	
	public UnpUserDetails(String username, String password, boolean enabled, Object usersVO)
	    throws IllegalArgumentException {
		this(username, password, enabled, true, true, true, AuthorityUtils.NO_AUTHORITIES, usersVO);
	}

	private Object usersVO;	

	public Object getUsersVO() {
		return usersVO;
	}

	public void setUsersVO(Object usersVO) {
		this.usersVO = usersVO;
	}
}
