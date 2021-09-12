package com.csf.whoami.base.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_VALUE_FORMAT("C0001", "Invalid format."),
    DATA_NOT_FOUND("C0002", "Data not found."),
    INTERNAL_ERROR("C0003", "Internal error."),
    REQUIRED_FIELD("C0004", "Required."),
    CONSTRAINT_VIOLATION("C0005", "Constraint violation."),
    METHOD_NOT_SUPPORTED("C0006", "method not supported."),
    MEDIA_TYPE_NOT_SUPPORTED("C0007", "Media type not supported."),
    BAD_CREDENTIALS("S0001", "Bad credentials."),
    FORBIDDEN("S0002", "Access denied."),
    TOKEN_INVALID("S0003", "Expired or invalid JWT token."),
    EXIST_LOGIN_ID("C0008", "Exist login id."),
    BAD_REQUEST("S0004", "Bad Request."),
    FILE_NOT_FOUND("C0009", "File not found."),
    FILE_UPLOAD_FAIL("C0010", "Upload file fail."),
    USER_NAME_REQUIRED("C0011", "Require Login Id."),
    PASSWORD_REQUIRED("C0012", "Require Password."),
    CURRENT_PASSWORD_EMPTY("C0013", "Current password is empty."),
    NEW_PASSWORD_EMPTY("C0014", "New password is empty."),
    CONFIRM_NEW_PASSWORD_EMPTY("C0015", "Confirm new password is empty."),
    NEW_AND_CONFIRM_PASSWORD_NOT_EQUALS("C0016", "New password and confirm new password not equals."),
    NEW_AND_OLD_PASSWORD_IS_SIMILAR("C0017", "New and old password is similar."),
    OLD_PASSWORD_IS_WRONG("C0018", "Old password is wrong."),
    INVALID_USERNAME_OR_PASSWORD("C0019", "Invalid Username or Password."),
    USER_IS_LOCK("S0020", "Your account has been temporarily locked."),
    ANT_SAVE_QUESTION("C0001", "Can not save question."),
    INVALID_FORMAT("C0002", "Invalid format."),
    INVALID_GROUP("C0003", "Invalid group area."),
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
    CANT_REGIST("C0015", "Save account not success."),
    CANT_FOUND_USER_ROLE("C0016", "Can not found user role."),
    CANT_SET_ROLE("C0017", "Can not set user role."),
    CANT_CREATE_GROUP("C0018", "Can not create group."),
    CANT_MAKE_EXAM("C0019", "Can not make examination."),
    PERMISSION_DENIED("CMS_0023", "User is not have permission."),
    PARAMETER_INVALID("CMS_0003", "Parameter invalid."),
    DATA_INVALID("CMS_0004", "Input data invalid."),
    CANT_INSERT_OPTIONS("CMS_0005", "Can not insert question options");

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
