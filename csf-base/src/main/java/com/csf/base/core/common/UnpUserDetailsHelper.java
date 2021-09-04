package com.csf.base.core.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class UnpUserDetailsHelper {
	
	protected static Log log = LogFactory.getLog(UnpUserDetailsHelper.class);

	/**
	 * 인증된 사용자객체를 VO형식으로 가져온다.
	 * 
	 * @return UsersVO - 사용자 ValueObject
	 * @throws Exception
	 */
	public static Object getAuthenticatedUser() throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication == null) {
			// log.debug("## authentication object is null!!");
			return null;
		}

		UnpUserDetails details = null;
		Object principal = authentication.getPrincipal();
		if (principal instanceof UnpUserDetails) {
			details = (UnpUserDetails) authentication.getPrincipal();
			return details.getUsersVO();
		} 

		// log.debug("## UnpUserDetailsHelper.getAuthenticatedUser :
		// AuthenticatedUser is " + details.getUsername());
		return null;
	}

	/**
	 * 인증된 사용자의 권한 정보를 가져온다. 예) [ROLE_ADMIN, ROLE_USER, ROLE_A, ROLE_B,
	 * ROLE_RESTRICTED, IS_AUTHENTICATED_FULLY, IS_AUTHENTICATED_REMEMBERED,
	 * IS_AUTHENTICATED_ANONYMOUSLY]
	 * 
	 * @return Collection - 사용자 권한정보 목록
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		List<String> authorities = new ArrayList<String>();
		Iterator<GrantedAuthority> it = (Iterator<GrantedAuthority>) authentication.getAuthorities().iterator();
		while (it.hasNext()) {
			authorities.add(it.next().getAuthority());
		}

		return authorities;
	}

	/**
	 * 인증된 사용자 여부를 체크한다.
	 * 
	 * @return Boolean - 인증된 사용자 여부(TRUE / FALSE)
	 */
	public static Boolean isAuthenticated() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication == null) {
			// log.debug("## authentication object is null!!");
			return Boolean.FALSE;
		}

		String username = authentication.getName();
		if (username.equals("roleAnonymous")) {
			// log.debug("## username is " + username);
			return Boolean.FALSE;
		}

		Object principal = authentication.getPrincipal();

		return Boolean.valueOf(principal != null);
	}

	/**
	 * 해당 권한 룰(권한 정보) 이 있는지 체크한여 true return
	 * 
	 * @param auth
	 * @return
	 */
	public static boolean isAuthenticated(String auth) {
		boolean result = true;

		try {
			if (getAuthorities().contains(auth)) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			result = false;
		}

		return result;
	}
}
