package com.csf.whoami.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.csf.base.ParameterContext;
import com.csf.base.WebFactory;
import com.csf.base.common.BaseController;
import com.csf.base.constant.ConstantsRequest;
import com.csf.base.core.ZValue;

@Controller
@RequestMapping("/{siteId}/{appId}/{pakageId}/{programId}")
public class WebController extends BaseController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private final UrlPathHelper urlPathHelper = new UrlPathHelper();

	@RequestMapping("/{targetMethod}.html")
	public ModelAndView invoke(HttpServletRequest request, HttpServletResponse response, ModelMap model,
								@PathVariable(ConstantsRequest.TARGET_METHOD) String targetMethod) throws Exception {
		ZValue paramVO = WebFactory.getAttributes(request);
		ModelAndView mv = null;
		model.addAttribute(ConstantsRequest.CONTEXT_PATH,request.getContextPath());
		mv = super.invoke(paramVO, request, response, model);
		return mv;
	}

	@Override
	protected String getProgramId(ParameterContext paramCtx){
		return paramCtx.getParam().getString(ConstantsRequest.PROGRAM_ID);
	}

	@Override
	protected String getTargetMethod(ParameterContext paramCtx) {
		return paramCtx.getParam().getString(ConstantsRequest.TARGET_METHOD);
	}
}
