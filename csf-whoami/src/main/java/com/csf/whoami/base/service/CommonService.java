package com.csf.whoami.base.service;

import com.csf.whoami.base.CommonListener;

public interface CommonService {

	public static final String SITE_ID = "siteId";
	public static final String BOS_SITE_ID = "bos";
	public static final String UCMS_SITE_ID = "ucms";
	public static final String PORTAL_SITE_ID = "portal";
	public static final String USR_SITE_ID = "usr";
	public static final String ENG_SITE_ID = "eng";
	public static final String CHN_SITE_ID = "chn";
	public static final String JPN_SITE_ID = "jpn";
	public static final String RUS_SITE_ID = "rus";
	public static final String MOBILE_SITE_ID = "mobile";
	public static final String HEALTH_SITE_ID = "health";
	public static final String EDU_SITE_ID = "edu";
	public static final String MEDIA_SITE_ID = "media";
	public static final String DONG_SITE_ID = "dong";
	public static final String DEPT_SITE_ID = "dept";
	public static final String MAYOR_SITE_ID = "mayor";
	public static final String ASSEMBLY_SITE_ID = "assembly";
	public static final String PROGRAM_ID = "programId";
	public static final String ATCH_FILE_ID = "atchFileId";
	public static final String RESULT_LIST = "resultList";
	public static final String RESULT = "result";
	public static final String PAGE_LINK = "pageLink";
	public static final String PAGE_QUERY_STRING = "pageQueryString";
	public static final String USER_INFO = "userInfo";
	public static final String CACHE_KEY = "cacheKey";

	public String getPageQueryData();

	public CommonListener getListener();
}
