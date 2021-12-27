package com.csf.whoami.adm;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.csf.base.ParameterContext;
import com.csf.base.WebFactory;
import com.csf.base.common.BaseController;
import com.csf.base.constant.ConstantsRequest;
import com.csf.base.core.ZValue;
import com.csf.base.service.common.CommonService;

@Controller
@RequestMapping("/adm/{compId}/{appId}/{pakageId}/{programId}")
public class MasterController extends BaseController {
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
	protected void doInvoke(CommonService service, ParameterContext paramCtx) throws Exception {
		String path = urlPathHelper.getLookupPathForRequest(paramCtx.getRequest());
//		String filename = WebUtils.extractFullFilenameFromUrlPath(path);
//		String extension = StringUtils.getFilenameExtension(filename);
//		extension = (StringUtils.hasText(extension)) ? extension.toLowerCase(Locale.ENGLISH) : null;
//		if ("json".equals(extension)) {
//			if (!getUnpJsonViewFinder().hasUnpJsonViewModel(paramCtx.getModel())) {
//				ZValue param = paramCtx.getParam();
//				log.debug("[{}.{}] Cannot find @UnpJsonView", param.getString("targetName"), param.getString("targetMethod"));
//				paramCtx.getResponse().sendRedirect("/cmmn/error.jsp");
//				return;
//			}
//		}
		super.doInvoke(service, paramCtx);
	}

	@Override
	protected ParameterContext setProperty(HttpServletRequest request, HttpServletResponse response, ZValue param, ModelMap model) throws Exception {
		ParameterContext paramCtx = super.setProperty(request, response, param, model);
		//패스버라이어블을 파라미터에 담기 VO형은 기본적으로 담겨져 있음
		@SuppressWarnings("unchecked")
		Map<String, String> uriTemplateVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if( uriTemplateVars != null ){
			param.putAll(uriTemplateVars);
		}
		model.addAttribute(ConstantsRequest.PARAM_VO, param);

		//사용자정보
//		MemberVO memberVO = (MemberVO)UnpUserDetailsHelper.getAuthenticatedUser();
//		model.addAttribute(CommonService.USER_INFO, memberVO);

		return paramCtx;
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
