package com.csf.base.utilities;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.csf.base.domain.response.AccountInfo;
import com.csf.base.exception.CustomException;
import com.csf.base.exception.ErrorCode;
import com.csf.base.exception.HttpStatus;

/**
 * The type User util.
 */
public final class AuthenticationUtils {

	/**
	 * Gets current user.
	 *
	 * @return the current user
	 */
	public static AccountInfo getCurrentUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			return null;
		}
		if (authentication.getPrincipal() instanceof AccountInfo) {
			return (AccountInfo) authentication.getPrincipal();
		}
		throw new CustomException(ErrorCode.BAD_CREDENTIALS.getMessage(),
				ErrorCode.BAD_CREDENTIALS.getCode(),
				HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Gets current user id.
	 *
	 * @return the current user id
	 */
	public static Long getCurrentUserId() {
		AccountInfo user = getCurrentUser();
		return user == null? null : user.getUserId();
	}

	/**
	 * Gets current username.
	 *
	 * @return the current username
	 */
	public static String getCurrentUsername() {
		AccountInfo user = getCurrentUser();
		return user == null? null : user.getUsername();
	}

	/**
	 * Gets current password.
	 *
	 * @return the current password
	 */
	public static String getCurrentPassword() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return (String) authentication.getCredentials();
		}
		throw new CustomException(ErrorCode.BAD_CREDENTIALS.getMessage(),
				ErrorCode.BAD_CREDENTIALS.getCode(),
				HttpStatus.UNAUTHORIZED);
	}
}