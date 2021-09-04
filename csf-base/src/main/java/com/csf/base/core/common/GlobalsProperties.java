package com.csf.base.core.common;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.csf.base.core.ZValue;
import com.csf.base.core.dao.ISqlDAO;

/**
 * 글로벌 프로퍼티
 * globals.propertis파일과 디비의 글로벌환경변수데이터를 읽어온다.
 * 같은 프로퍼티명이 있을경우 globals.propertis가 우선
 * @author KHD
 *
 */
@Component
public class GlobalsProperties implements InitializingBean {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name="prop")
	private Properties fileProperties;

	@Autowired
	private Environment env;

	private ZValue propertyVal = new ZValue();

	private String osType = "";

	private String dbType = "";

	private String testMode = "N";

	private String webRootPath = "";

	private String contentsPathRoot = "";

	private String fileStorePath = "";

	private String domain = "";

	private String baseTmplatPath = "";
	private String mainPath = "";
	private String layoutPath = "";
	private String tmplatPath = "";
	private String cssPath = "";
	private String jsPath = "";

	/**
	 * 디폴트 첨부가능한 파일확장자 ','로 구분 ex) Globals.allowExt=gif,jpg,doc
	 * 각 서비스별로는 Service.serviceName.allowExt ex) Service.siteMng.allowExt=gif,jpg
	 */
	private String allowExt = "";

	private long limitFileSize = 0L;

	@Resource(name = "SqlDAO")
	private ISqlDAO<ZValue> sqlDao;

	public String getString(String key) {
		return propertyVal.getString(key);
	}

	public Properties getFileProperties() {
		return this.fileProperties;
	}

	public String getOsType() {
		return osType;
	}

	public String getDbType() {
		return dbType;
	}

	public ZValue getPropertyVal() {
		return propertyVal;
	}

	public String getTestMode() {
		return testMode;
	}

	public String getContentsPathRoot() {
		return contentsPathRoot;
	}

	public String getWebRootPath() {
		return webRootPath;
	}

	public String getFileStorePath() {
		return fileStorePath;
	}

	public String getAllowExt() {
		return allowExt;
	}


	public long getLimitFileSize() {
		return limitFileSize;
	}

	public String getDomain() {
		return domain;
	}

	public String getBaseTmplatPath() {
		return baseTmplatPath;
	}

	public void setBaseTmplatPath(String baseTmplatPath) {
		this.baseTmplatPath = baseTmplatPath;
	}

	public String getMainPath() {
		return mainPath;
	}

	public void setMainPath(String mainPath) {
		this.mainPath = mainPath;
	}

	public String getLayoutPath() {
		return layoutPath;
	}

	public void setLayoutPath(String layoutPath) {
		this.layoutPath = layoutPath;
	}

	public String getTmplatPath() {
		return tmplatPath;
	}

	public void setTmplatPath(String tmplatPath) {
		this.tmplatPath = tmplatPath;
	}

	public String getCssPath() {
		return cssPath;
	}

	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
	}

	public String getJsPath() {
		return jsPath;
	}

	public void setJsPath(String jsPath) {
		this.jsPath = jsPath;
	}

	public void reload() throws Exception {
		load();
	}
	
	public boolean allowProfile(String profiles) {
        final String[] allowedProfiles = StringUtils.split(profiles, ",");
        boolean allowed = false;
        for (String profile : env.getActiveProfiles()) {
            if (ArrayUtils.contains(allowedProfiles, profile)) {
                allowed = true;
                break;
            }
        }
        return allowed;
	}

	public boolean isReal() {
		String[] profiles = env.getActiveProfiles();
		if (ArrayUtils.contains(profiles, "real")) {
			return true;
		}
		String[] defaultProfiles = env.getDefaultProfiles();
		if (ArrayUtils.isEmpty(profiles) && ArrayUtils.contains(defaultProfiles, "real")) {
			return true;
		}
		return false;
	}

	protected void load() throws Exception {
		List<ZValue> propertyList = sqlDao.findAll("findAllGlobals");
		for (ZValue val : propertyList) {
			//putObject 키값 CamelCase변환하여 넣지 않음
			propertyVal.putObject(val.getString("attrbNm"), val.getString("attrbValue"));
		}
		//globals.properties가 디비 property값 오버라이드
		ZValue fileVal = new ZValue();
		fileVal.putAllObject(fileProperties);
		propertyVal.putAllObject(fileVal);

		osType = propertyVal.getString("Globals.OsType");
		dbType = propertyVal.getString("Globals.DbType");
		testMode = propertyVal.getString("Globals.testMode");
		webRootPath = propertyVal.getString("Globals.webRootPath");
		fileStorePath = propertyVal.getString("Globals.fileStorePath");
		allowExt = propertyVal.getString("Globals.allowExt");
		limitFileSize = propertyVal.getLong("Globals.limitFileSize");
		domain = propertyVal.getString("Globals.domain");
		contentsPathRoot = propertyVal.getString("Globals.contentsPathRoot");

		/*
		 * 템플릿 및 레이아웃 설정 2017.09.19(lkh)
		 */
		baseTmplatPath = propertyVal.getString("Globals.estbsFilePath.baseTmplat");
		mainPath = propertyVal.getString("Globals.estbsFilePath.main");
		layoutPath = propertyVal.getString("Globals.estbsFilePath.layout");
		tmplatPath = propertyVal.getString("Globals.estbsFilePath.tmplat");
		cssPath = propertyVal.getString("Globals.estbsFilePath.css");
		jsPath = propertyVal.getString("Globals.estbsFilePath.js");

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		load();
	}
}
