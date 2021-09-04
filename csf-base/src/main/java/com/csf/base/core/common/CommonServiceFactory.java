package com.csf.base.core.common;

import com.csf.base.core.ParameterContext;

public interface CommonServiceFactory {
	
	public static final String DEFAULT_KEY = "default";
	
	public CommonService getService(ParameterContext paramCtx) throws Exception;
	
	public ModelAndViewResolver getModelAndViewResolver(ParameterContext paramCtx) throws Exception;
}
