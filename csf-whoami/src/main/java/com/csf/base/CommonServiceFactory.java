package com.csf.base;

import com.csf.base.service.CommonService;

public interface CommonServiceFactory {

	public static final String DEFAULT_KEY = "default";

	public CommonService getService(ParameterContext paramCtx) throws Exception;

	public ModelAndViewResolver getModelAndViewResolver(ParameterContext paramCtx) throws Exception;
}
