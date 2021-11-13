package com.csf.whoami.adm.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.csf.base.ParameterContext;
import com.csf.base.core.ZValue;
import com.csf.base.service.DefaultCrudService;

@Service
public class MenuService extends DefaultCrudService {

	@Override
	public void list(ParameterContext paramCtx) throws Exception {
		super.list(paramCtx);
		ZValue param = paramCtx.getParam();
		ModelMap model = paramCtx.getModel();
		model.addAttribute("pageNm", param.getString("pageNm"));
	}
}
