package com.csf.base.service.common;

import com.csf.base.ParameterContext;
import com.csf.base.mvr.ModelAndViewResolver;

public interface CommonServiceFactory {

	public static final String DEFAULT_KEY = "default";

	public CommonService getService(ParameterContext paramCtx) throws Exception;

	public ModelAndViewResolver getModelAndViewResolver(ParameterContext paramCtx) throws Exception;
}
