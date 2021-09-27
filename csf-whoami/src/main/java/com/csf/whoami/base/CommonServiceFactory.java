package com.csf.whoami.base;

import com.csf.whoami.base.service.CommonService;

public interface CommonServiceFactory {

	public static final String DEFAULT_KEY = "default";

	public CommonService getService(ParameterContext paramCtx) throws Exception;

	public ModelAndViewResolver getModelAndViewResolver(ParameterContext paramCtx) throws Exception;
}
