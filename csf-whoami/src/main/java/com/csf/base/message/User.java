package com.csf.base.message;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.csf.utilities.Browser;

import lombok.Data;

@Data
public class User implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	public static final String USER_INFO = "userinfo";

	private String dataSchemaName;

	private String sessionNo;

	private String userid;

	private String userName;

	private String kanriKbn;

	private String headerFile;

	private Timestamp loginTime;

	private String fmtLoginTime;

	private String adminDept;

	private String companyName;

	private String companyPostCode;

	private String companyAddr1;

	private String companyAddr2;

	private String companyTel;

	private String email;

	private String comment;

	private String projectId;

	private String projectName;

	private String special;

	private String sysStartTime;

	private String sysEndTime;

	private String fmtSysTime;

	private String timeoutTime;

	private String accessLevel;

	private String logoutUrl;

	private String logoutAction;

	private String programId;

	private Browser browser;

	private Locale locale;
	private TimeZone timezone;
	private String ediCharEncoding;
	private String ediFileExtension;
	private Map<String, List<String>> buttonControlInfo;
	private String gengoCode;

	private String sokoCode;
	private String sokoName;
	private String kitakushaCode;
	private String kitakushaName;

	private Map <String, String> extraUser;

	public User() {
		super();
	}

	public User(Map<?,?> ninsyoInfo) {
		super();
		//SIS�F�؏�񂩂�}�b�s���O
		setUserid((String)ninsyoInfo.get("loginId"));
		setUserName((String)ninsyoInfo.get("adminName"));
		setSessionNo((ninsyoInfo.get("sessionNo")==null)?"":ninsyoInfo.get("sessionNo").toString());
		setHeaderFile((String)ninsyoInfo.get("headerFile"));
		//setLoginTime(new Timestamp((long) ninsyoInfo.get("loginTime")));
		setFmtLoginTime((String)ninsyoInfo.get("fmtLoginTime"));
		setAdminDept((String)ninsyoInfo.get("adminDept"));
		setCompanyName((String)ninsyoInfo.get("companyName"));
		setCompanyPostCode((String)ninsyoInfo.get("companyPostCode"));
		setCompanyAddr1((String)ninsyoInfo.get("companyAddr1"));
		setCompanyAddr2((String)ninsyoInfo.get("companyAddr2"));
		setCompanyTel((String)ninsyoInfo.get("companyTel"));
		setEmail((String)ninsyoInfo.get("email"));
		setComment((String)ninsyoInfo.get("comment"));
		setProjectId((String)ninsyoInfo.get("projectId"));
		setProjectName((String)ninsyoInfo.get("projectName"));
		setSpecial((String)ninsyoInfo.get("special"));
		setSysStartTime((String)ninsyoInfo.get("sysStartTime"));
		setSysEndTime((String)ninsyoInfo.get("sysEndTime"));
		setFmtSysTime((String)ninsyoInfo.get("fmtSysTime"));
		setTimeoutTime((ninsyoInfo.get("timeoutTime")==null)?"":ninsyoInfo.get("timeoutTime").toString());//Integer�^
		setAccessLevel((ninsyoInfo.get("accessLevel")==null)?"":ninsyoInfo.get("accessLevel").toString());//Integer�^
		setLogoutUrl((String)ninsyoInfo.get("logoutSSOUrl"));
		setLogoutAction((String)ninsyoInfo.get("action"));
		//�q�ɕʐݒ肩��}�b�s���O
		setDataSchemaName((String)ninsyoInfo.get("dataSchemaName"));
		setSokoCode((String)ninsyoInfo.get("sokoCode"));
		setSokoName((String)ninsyoInfo.get("sokoName"));
		setKanriKbn((String)ninsyoInfo.get("kanriKbn"));

	}


	/**
	 * ���[�U���̃R�s�[�����R�ɍ쐬�ł���悤�ɂ���B
	 *
	 * @return ���[�U���̃R�s�[
	 */
	public Object clone() {
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException cnse) {
			//�N���[�����T�|�[�g���Ă��邽�߂��肦�Ȃ�
			throw new InternalError(cnse.toString());
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString());
		sb.append("dataSchemaName=").append(getDataSchemaName());
		sb.append(",sessionNo=").append(getSessionNo());
		sb.append(",userName=").append(getUserName());
		sb.append(",userId=").append(getUserid());
		sb.append(",kanriKbn=").append(getKanriKbn());
		sb.append(",sokoName=").append(getSokoName());
		sb.append(",sokoCode=").append(getSokoCode());
		sb.append(",kitakushaName=").append(getKitakushaName());
		sb.append(",kitakushaCode=").append(getKitakushaCode());
		sb.append(",locale=").append(getLocale());
		sb.append(",ediCharEncoding=").append(getEdiCharEncoding());
		sb.append(",ediFileExtension=").append(getEdiFileExtension());
		getExtraUser().entrySet().stream().forEach(e -> sb.append(",").append(e.getKey()).append("=").append(e.getValue()));
		return sb.toString();
	}

	public static Locale createLocale(String localeString) {
		if(localeString != null) {
			Pattern p = Pattern.compile("^(\\p{Alpha}{1,8})([\\_]\\p{Alpha}{1,8})?([\\_]\\p{Alpha}{1,8})?$");
			Matcher m = p.matcher(localeString);
			if(m.matches()) {
				String[] arr = new String[]{m.group(1), m.group(2), m.group(3)};
				if(arr[2] != null && arr[2].length() > 0) {
					return new Locale(arr[0], arr[1].substring(1), arr[2].substring(1));
				} else {
					if(arr[1] != null && arr[1].length() > 0) {
						return new Locale(arr[0], arr[1].substring(1));
					} else {
						return new Locale(arr[0]);
					}
				}
			}
		}
		return Locale.getDefault();
	}

	public static String getGengo(Locale locale) {
		User user = new User();
		user.setLocale(locale);
		return Message.getMessage(user, "system.gengoCode");
	}

	public void setLocale(Locale locale) {
	    this.locale = locale;
	    setGengoCode(Message.getMessage(this, "system.gengoCode"));
	}
}
