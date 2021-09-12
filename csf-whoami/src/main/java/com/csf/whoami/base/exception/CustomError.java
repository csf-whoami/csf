package com.csf.whoami.base.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomError {
    @JsonProperty("field")
    private String field;
    @JsonProperty("code")
    private String code;
    @JsonProperty("params")
    private String[] params;
    @JsonProperty("default_message")
    private String defaultMessage;

    public CustomError() {
    }

    public CustomError(String code, String[] params, String field, String defaultMessage) {
        this.code = code;
        this.params = params;
        this.field = field;
        this.defaultMessage = defaultMessage;
    }

    public CustomError(String field, String code, String defaultMessage) {
        this.field = field;
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * @param field
     *            the field to set
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the params
     */
    public String[] getParams() {
        return params;
    }

    /**
     * @param params
     *            the params to set
     */
    public void setParams(String[] params) {
        this.params = params;
    }

    /**
     * @return the defaultMessage
     */
    public String getDefaultMessage() {
        return defaultMessage;
    }

    /**
     * @param defaultMessage
     *            the defaultMessage to set
     */
    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    static public CustomError[] errorsFromException(Exception ex) {
        CustomError error = new CustomError("", "", ex.getMessage());
        CustomError[] errors = { error };
        return errors;
    }

    static public CustomError badRequest(String field) {
        return new CustomError(field, "", "Bad Request");
    }
}
