package com.csf.whoami.base;

public interface CommonListener {

	public void before(ParameterContext paramCtx) throws Exception;

	public void after(ParameterContext paramCtx) throws Exception;
}
