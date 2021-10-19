package com.csf.base.mvr;

import org.springframework.web.servlet.ModelAndView;

import com.csf.base.ParameterContext;

public interface ModelAndViewResolver {

	public static final String INVALID = "invalid";
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	public static final String SERVER_FAIL = "serverFail";
	public static final String MODIFY_FAIL = "modifyFail";
	public static final String ERROR = "error";
	public static final String REPLY_SUCCESS = "replySuccess";
	public static final String BINDING_RESULT_ERROR = "bindingResultError";

	public static final String HIDDEN_INPUT_KEY = "hiddenInput";
	public static final String GO_URL_KEY = "goUrl";
	public static final String RESULT_CODE_KEY = "resultCode";
	public static final String MSG_KEY = "msg";
	public static final String CONFIRM_MSG_KEY = "confirmMsg";
	public static final String CONFIRM_BACK_URL = "confirmBackUrl";
	public static final String WINDOW_MODE = "windowMode";
	public static final String CONFIRM_URL_WINDOW_MODE = "confirmUrlWindowMode";
	public static final String WIN_CLOSE_WINDOW_MODE = "winCloseWindowMode";
	public static final String WIN_CLOSE_RELOAD_WINDOW_MODE = "winCloseReloadWindowMode";
	public static final String WIN_CLOSE_LOCATION_WINDOW_MODE = "winCloseLocationWindowMode";
	public static final String WIN_CLOSE_LOCATION_REAL_WINDOW_MODE = "winCloseLocationRealNameWindowMode";

	public static final String STREAM_WINDOW_MODE = "streamWindowMode";

	public static final String INCLUDE_PAGE = "includePage";
	public static final String INCLUDE_DEFAULT_PAGE = "/cts/cmmn/ready.jsp";
	public static final String CONTENTS_PREVEW_PARAM = "cntntsCn";
	public static final String JSP_PAGE = "jspPage";


	public ModelAndView getModelAndView(ParameterContext paramCtx) throws Exception;
}
