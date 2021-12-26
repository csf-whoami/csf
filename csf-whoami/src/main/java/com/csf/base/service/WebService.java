package com.csf.base.service;

import com.csf.base.ParameterContext;
import com.csf.base.service.common.CommonService;

public interface WebService extends CommonService {
    public void index(ParameterContext paramCtx);
    public void execute(ParameterContext paramCtx);
}
