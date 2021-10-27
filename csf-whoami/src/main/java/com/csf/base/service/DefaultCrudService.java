package com.csf.base.service;

import com.csf.base.CommonListener;
import com.csf.base.ParameterContext;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DefaultCrudService extends AbstractCrudService {

	private CommonListener listener;
	private String pageQueryData;

	protected void doList(ParameterContext paramCtx, String listQueryId, String countQueryId) throws Exception {
		paramCtx.setSqlDAO(sqlDao);
//		QueryIdVO qvo = new QueryIdVO();
//		qvo.setListQueryId(listQueryId);
//		qvo.setCountQueryId(countQueryId);
//		paramCtx.setQueryIdVO(qvo);
//		paramCtx.setPageQueryData(getPageQueryData());
//		if (paramCtx.getPageQuery() == null) {
//			paramCtx.setPageQuery(pageQuery);
//		}
//		paramCtx.setPageInfo(pageInfo);
//		listHandler.invoke(paramCtx);
		System.out.println("Go to list page.");
	}

	protected void doInsert(ParameterContext paramCtx, String insertQueryId) throws Exception {
//		ZValue param = paramCtx.getParam();
//
//		boolean flag = uploadFile(paramCtx);
//		if (!flag) return;
//
//		UsersVO user = (UsersVO)UnpUserDetailsHelper.getAuthenticatedUser();
//		if (user != null) {
//			param.put("updtId", user.getUserId());
//			param.put("registId", user.getUserId());
//		}
//		sqlDao.save(insertQueryId, param);
//		Object pkValue = param.get("unqKey");
//		paramCtx.setPkValue(pkValue);
//		ModelMap model = paramCtx.getModel();
//		MVUtils.setResultProperty(model, SUCCESS, messageSource.getMessage("success.common.insert", null, Locale.getDefault()));
		System.out.println("Go to doInsert page.");
	}

	protected void doView(ParameterContext paramCtx, String viewQueryId) throws Exception {
//		ZValue param = paramCtx.getParam();
//		ModelMap model = paramCtx.getModel();
//
//		ZValue result = sqlDao.findOne(viewQueryId, param);
//		model.addAttribute(RESULT, result);
//		getFiles(paramCtx);
		System.out.println("Go to doView page.");
	}
}
