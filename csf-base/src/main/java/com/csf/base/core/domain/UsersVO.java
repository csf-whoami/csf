package com.csf.base.core.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UsersVO implements Serializable {

	private static final long serialVersionUID = 2876900063125999565L;

	/**
	 * 일련번호
	 */
	private long userSn = 0l;

	/**
	 * 인증키값
	 */
	private String userPin = "";

	/**
	 * 이름
	 */
	private String userNm = "";

	/**
	 * 아이디
	 */
	private String userId = "";

	/**
	 * 패스워드
	 */
	private String password = "";

	/**
	 * 패스워드 확인
	 */
	private String password2 = "";

	/**
	 * 권한코드
	 */
	private String authorCd = "";

	/**
	 * 관리자여부
	 */
	private String mngrAt = "";

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
