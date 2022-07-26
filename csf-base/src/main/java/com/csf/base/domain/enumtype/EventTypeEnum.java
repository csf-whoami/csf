package com.csf.base.domain.enumtype;

import lombok.Getter;

@Getter
public enum EventTypeEnum {

	CREATE_GROUP("WTGC100"	, "CREATE GROUP"),
	INVITE_JOIN_GROUP("WTGC101"	, "INVITE JOIN GROUP"),
	JOIN_GROUP_WITHOUT_INVITE("WTGC101"	, "JOIN GROUP WITHOUT INVITE"),
	JOIN_GROUP_BY_INVITE("WTGC111"	, "JOIN GROUP BY INVITE"),
	CREATE_CHANNEL("WTCC100"	, "CREATE CHANNEL"),
	INVITE_JOIN_CHANNEL("WTCC101"	, "INVITE JOIN CHANNEL"),
	JOIN_CHANNEL_WITHOUT_INVITE("WTCC101"	, "JOIN CHANNEL WITHOUT INVITE"),
	JOIN_CHANNEL_BY_INVITE("WTCC111"	, "JOIN CHANNEL BY INVITE"),
    CREATE_QUESTION("WTQC100"	, "CREATE QUESTION");

    private String code;
    private String value;

    EventTypeEnum(String code, String message) {
        this.code = code;
        this.value = message;
    }
}