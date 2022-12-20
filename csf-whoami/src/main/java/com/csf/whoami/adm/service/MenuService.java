package com.csf.whoami.adm.service;

import com.csf.base.domain.mainpage.MenuGroupInfo;
import com.csf.base.domain.request.ConfirmGroupInfo;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.csf.base.ParameterContext;
import com.csf.base.core.ZValue;
import com.csf.base.service.impl.AdmMasterService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService extends AdmMasterService {

	@Override
	public void list(ParameterContext paramCtx) throws Exception {
		super.list(paramCtx);
		ZValue param = paramCtx.getParam();
		ModelMap model = paramCtx.getModel();
		model.addAttribute("pageNm", param.getString("pageNm"));
	}
}
