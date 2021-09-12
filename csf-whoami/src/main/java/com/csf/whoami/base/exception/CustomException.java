package com.csf.whoami.base.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String errorCode;
	private final String message;
	private final HttpStatus httpStatus;

	/**
	 * @param message
	 * @param httpStatus
	 * @description Constructor with parameter
	 */
	public CustomException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.errorCode = "";
	}
}
