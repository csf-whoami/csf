/**
 * 
 */
package com.csf.security.exception;

/**
 * @author tuan
 *
 */
public enum ExceptionError {

	CANT_SAVE_QUESTION("C0001", "Can not save question.");

	private String code;
	private String message;

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets code.
	 *
	 * @param code the code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets message.
	 *
	 * @param message the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	ExceptionError(String code, String message) {
	        this.code = code;
	        this.message = message;
	    }
}
