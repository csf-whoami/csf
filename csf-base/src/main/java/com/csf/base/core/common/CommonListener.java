package com.csf.base.core.common;

import com.csf.base.core.ParameterContext;

public interface CommonListener {

	public void before(ParameterContext paramCtx) throws Exception;

	public void after(ParameterContext paramCtx) throws Exception;
}
