package com.csf.whoami.base.exception;

import lombok.Getter;

public enum EProgramType {

    ADMIN("adm"),
    API("api"),
    WEB("web");

	@Getter
	private String value;
	EProgramType(String value) {
        this.value = value;
    }
}
