package com.csf.base.domain.enumtype;

import lombok.Getter;

@Getter
public enum AccountTypeEnum {

	SYSTEM("SYSTEM", "SYSTEM"),
	CSF("CSF", "CSF_TYPE"),
	EMAIL("EMAIL", "EMAIL_TYPE"),
    WHOAMI("WHOAMI", "W_TYPE");

    private String code;
    private String value;

    AccountTypeEnum(String code, String message) {
        this.code = code;
        this.value = message;
    }
}