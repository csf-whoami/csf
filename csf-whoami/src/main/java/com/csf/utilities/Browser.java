/*
 * -----------------------------------------------------------------------
 * @(#)Browser.java
 *
 * Copyright 2003 Seino Information Service Co.,Ltd. All rights reserved.
 *
 * -----------------------------------------------------------------------
 * �V�X�e�����@�@�F�r�k�h�l�r�|�v�g
 * �T�u�V�X�e�����F����									
 * �v���O�������@�F�F�؃V�X�e���ɂĎ擾�ł���u���E�U���
 * -----------------------------------------------------------------------
 * �N���X���F Action
 * �쐬�����F2006.06.30 K05-292 T.Ishii �V�K�쐬
 * �C�������F2003.99.99 XXX-XXX ���O       �C�����e
 * �C�������F 
 * ---------------com.csf.utilities----------------------
 */
package com.csf.utilities;

import java.io.Serializable;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Browser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String agent;
	private String maker;
	private String serial;
	private boolean mobile;
	
	public Browser() {
	}

	public Browser(HttpServletRequest request) {
		Hashtable<String, Object> hash = UTBrowserCheck.getBrowserData(request);
		setAgent((String)hash.get("AGENT"));
		setMaker(toMaker((String)hash.get("MAKER")));//���[�J��SLIMS�p�ɕϊ�����
		setSerial((String)hash.get("SERIAL"));
		setMobile(((Boolean)hash.get("ISMOBILE")).booleanValue());
	}

	public String toMaker(String maker) {
		return "Other";
	}
}
