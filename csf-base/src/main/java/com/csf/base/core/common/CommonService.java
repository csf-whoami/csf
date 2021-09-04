package com.csf.base.core.common;

import com.csf.base.core.paging.PageQuery;

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

	public static final String INVALID = ModelAndViewResolver.INVALID;
	public static final String SUCCESS = ModelAndViewResolver.SUCCESS;
	public static final String FAIL = ModelAndViewResolver.FAIL;
	public static final String MODIFY_FAIL = ModelAndViewResolver.MODIFY_FAIL;
	public static final String ERROR = ModelAndViewResolver.ERROR;
	public static final String REPLY_SUCCESS = ModelAndViewResolver.REPLY_SUCCESS;
	public static final String BINDING_RESULT_ERROR = ModelAndViewResolver.BINDING_RESULT_ERROR;

	public static final String HIDDEN_INPUT_KEY = ModelAndViewResolver.HIDDEN_INPUT_KEY;
	public static final String GO_URL_KEY = ModelAndViewResolver.GO_URL_KEY;
	public static final String RESULT_CODE_KEY = ModelAndViewResolver.RESULT_CODE_KEY;
	public static final String MSG_KEY = ModelAndViewResolver.MSG_KEY;
	public static final String CONFIRM_MSG_KEY = ModelAndViewResolver.CONFIRM_MSG_KEY;
	public static final String WINDOW_MODE = ModelAndViewResolver.WINDOW_MODE;
	public static final String CONFIRM_URL_WINDOW_MODE = ModelAndViewResolver.CONFIRM_URL_WINDOW_MODE;
	public static final String WIN_CLOSE_WINDOW_MODE = ModelAndViewResolver.WIN_CLOSE_WINDOW_MODE;
	public static final String WIN_CLOSE_RELOAD_WINDOW_MODE = ModelAndViewResolver.WIN_CLOSE_RELOAD_WINDOW_MODE;
	public static final String WIN_CLOSE_LOCATION_WINDOW_MODE = ModelAndViewResolver.WIN_CLOSE_LOCATION_WINDOW_MODE;
	public static final String STREAM_WINDOW_MODE = ModelAndViewResolver.STREAM_WINDOW_MODE;

	public GlobalsProperties getGlobalsProperties();

	public CommonListener getListener();

	public String getPageQueryData();

	public PageQuery getPageQuery();
}
