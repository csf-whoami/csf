package com.csf.base.core.domain;

import java.util.Date;

public class MemberVO extends UsersVO {

	private static final long serialVersionUID = -8000593049986438363L;


	/**
	 * 사용자타입코드
	 */
	private String userTyCd = "";

	/**
	 * 사용자이메일주소
	 */
	private String userEmad = "";

	/**
	 * 사용자휴대전화번호
	 */
	private String userCpno = "";

	/**
	 * 인증구분코드
	 */
	private String crtfcSeCd = "";

	/**
	 * 미성년자인증여부(14세미만: 'Y')
	 */
	private String chldCrtfcAt = "";

	/**
	 * 생년월일
	 */
	private String brthdy = "";

	/**
	 * 성별코드
	 */
	private String sexCd = "";

	/**
	 * 로그인일시
	 */
	private Date lastLoginDt = null;

	/**
	 * 비밀번호변경일시
	 */
	private Date passwordChangeDt = null;

	/**
	 * 약관동의일시
	 */
	private Date stplatAgreDt = null;

	/**
	 * 부서명
	 */
	private String deptNm = "";

	/**
	 * 회원가입 인증 타입
	 */
	private String GlobalsSiteSe = "";

	public String getUserTyCd() {
		return userTyCd;
	}

	public void setUserTyCd(String userTyCd) {
		this.userTyCd = userTyCd;
	}

	public String getUserEmad() {
		return userEmad;
	}

	public void setUserEmad(String userEmad) {
		this.userEmad = userEmad;
	}

	public String getUserCpno() {
		return userCpno;
	}

	public void setUserCpno(String userCpno) {
		this.userCpno = userCpno;
	}

	public String getCrtfcSeCd() {
		return crtfcSeCd;
	}

	public void setCrtfcSeCd(String crtfcSeCd) {
		this.crtfcSeCd = crtfcSeCd;
	}

	public String getChldCrtfcAt() {
		return chldCrtfcAt;
	}

	public void setChldCrtfcAt(String chldCrtfcAt) {
		this.chldCrtfcAt = chldCrtfcAt;
	}

	public String getBrthdy() {
		return brthdy;
	}

	public void setBrthdy(String brthdy) {
		this.brthdy = brthdy;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public Date getLastLoginDt() {
		return lastLoginDt;
	}

	public void setLastLoginDt(Date lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}

	public Date getPasswordChangeDt() {
		return passwordChangeDt;
	}

	public void setPasswordChangeDt(Date passwordChangeDt) {
		this.passwordChangeDt = passwordChangeDt;
	}

	public Date getStplatAgreDt() {
		return stplatAgreDt;
	}

	public void setStplatAgreDt(Date stplatAgreDt) {
		this.stplatAgreDt = stplatAgreDt;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getGlobalsSiteSe() {
		return GlobalsSiteSe;
	}

	public void setGlobalsSiteSe(String globalsSiteSe) {
		GlobalsSiteSe = globalsSiteSe;
	}

}
