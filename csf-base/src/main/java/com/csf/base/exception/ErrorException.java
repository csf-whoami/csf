/**
 * 
 */
package com.csf.base.exception;

public enum ErrorException {

	CANT_SAVE_QUESTION("C0001", "Can not save question."),
	INVALID_FORMAT("C0002", "Invalid format."),
	INVALID_GROUP("C0003", "Invalid group area."),
	BAD_CREDENTIALS("C0004", "Bad redentials."),
	INVALID_USER("C0005", "Invalid user."),
	CANT_INVITE("C0006", "Can not invite user."),
	CANT_NOTIFICATION("C0007", "Can not notify to user."),
	NOT_EXIST_INVITE("C0008", "Can not found invite."),
	INVITE_EXPIRED("C009", "Invite is over expired."),
	GROUP_NO_QUESTIONS("C010", "Group with no question."),
	CANT_SAVE_QUIZ("C011", "Can not save Quiz test."),
	NOT_EXIST_QUIZ("C012", "Not exist quiz test."),
	INVALID_QUIZ("C013", "Invalid quiztest."),
	ANSWER_NOT_MAP("C014", "The answers not mapping."),
	CANT_REGIST("C0015", "Save account not success"),
	CANT_FOUND_USER_ROLE("C0016", "Can not found user role."),
	CANT_SET_ROLE("C0017", "Can not set user role."),
	CANT_CREATE_GROUP("C0018", "Can not create group."),
	CANT_MAKE_EXAM("C0019", "Can not make examination."),
	PERMISSION_DENIED("CMS_0023", "User is not have permission."),
	PARAMETER_INVALID("CMS_0003", "Parameter invalid");

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

	ErrorException(String code, String message) {
	        this.code = code;
	        this.message = message;
	    }
}
