package com.csf.database.type;

import lombok.Getter;

@Getter
public enum EnumGroupType {

    CHANNEL("T001", "CHANNEL"),
    QUESTION("T002", "QUESTION");

    private String code;
    private String value;

    EnumGroupType(String code, String message) {
        this.code = code;
        this.value = message;
    }
}