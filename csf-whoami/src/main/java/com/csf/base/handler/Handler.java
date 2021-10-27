package com.csf.base.handler;

import com.csf.base.ParameterContext;

public interface Handler {

	public static final boolean CONTINUE = true;
	public static final boolean STOP = false;

	public boolean invoke(ParameterContext paramCtx) throws Exception;
}
