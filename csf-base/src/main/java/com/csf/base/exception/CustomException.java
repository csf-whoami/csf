/**
 * 
 */
package com.csf.base.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -3163757886763387095L;

	private final String message;
	private final String code;
	private final HttpStatus httpStatus;

	/**
	 * Instantiates a new Custom exception.
	 *
	 * @param message    the message
	 * @param code       the code
	 * @param httpStatus the http status
	 */
	public CustomException(String message, String code, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Gets http status.
	 *
	 * @return the http status
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
}
