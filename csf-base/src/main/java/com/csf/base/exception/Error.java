package com.csf.base.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * The enum Error.
 */
@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public enum Error {

    INVALID_VALUE_FORMAT("C0001", "Invalid format"),
    DATA_NOT_FOUND("C0002", "Data not found"),
    INTERNAL_ERROR("C0003", "Internal error"),
    REQUIRED_FIELD("C0004", "Required"),
    CONSTRAINT_VIOLATION("C0005", "Constraint violation"),
    METHOD_NOT_SUPPORTED("C0006", "method not supported"),
    MEDIA_TYPE_NOT_SUPPORTED("C0007", "Media type not supported"),
    BAD_CREDENTIALS("S0001", "Bad credentials"),
    FORBIDDEN("S0002", "Access denied"),
    TOKEN_INVALID("S0003", "Expired or invalid JWT token"),
    EXIST_LOGIN_ID("C0008", "Exist login id"),
    BAD_REQUEST("S0004", "Bad Request"),
    FILE_NOT_FOUND("C0009", "File not found"),
    FILE_UPLOAD_FAIL("C0010", "Upload file fail"),
    USER_NAME_REQUIRED("C0011", "Require Login Id"),
    PASSWORD_REQUIRED("C0012", "Require Password"),
    CURRENT_PASSWORD_EMPTY("C0013", "Current password is empty"),
    NEW_PASSWORD_EMPTY("C0014", "New password is empty"),
    CONFIRM_NEW_PASSWORD_EMPTY("C0015", "Confirm new password is empty"),
    NEW_AND_CONFIRM_PASSWORD_NOT_EQUALS("C0016", "New password and confirm new password not equals"),
    NEW_AND_OLD_PASSWORD_IS_SIMILAR("C0017", "New and old password is similar"),
    OLD_PASSWORD_IS_WRONG("C0018", "Old password is wrong"),
    INVALID_USERNAME_OR_PASSWORD("C0019", "Invalid Username or Password"),
    USER_IS_LOCK("S0020", "Your account has been temporarily locked.");

    private String code;
    private String message;

    Error(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
