package com.csf.base.service;

import com.csf.base.ParameterContext;
import com.csf.base.service.common.CommonService;

public interface CrudService extends CommonService {

	public void index(ParameterContext paramCtx) throws Exception;

	public void list(ParameterContext paramCtx) throws Exception;

	public void view(ParameterContext paramCtx) throws Exception;

	public void forInsert(ParameterContext paramCtx) throws Exception;

	public void insert(ParameterContext paramCtx) throws Exception;

	public void forUpdate(ParameterContext paramCtx) throws Exception;

	public void update(ParameterContext paramCtx) throws Exception;

	public void delete(ParameterContext paramCtx) throws Exception;

	public void uploadExcel(ParameterContext paramCtx) throws Exception;
	
	public void downloadExcel(ParameterContext paramCtx) throws Exception;
}
