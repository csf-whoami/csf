package com.csf.base.common;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.CommonListener;
import com.csf.base.ParameterContext;
import com.csf.base.constant.ConstantsRequest;
import com.csf.base.core.ZValue;
import com.csf.base.mvr.ModelAndViewResolver;
import com.csf.base.service.CommonService;
import com.csf.base.utilities.StrUtils;
import com.csf.base.utilities.StringUtils;
import com.csf.base.utilities.UnpCollectionUtils;

public abstract class BaseController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private Map<String, Method> methodCache = UnpCollectionUtils.lruCache(1000);

	@Autowired
	protected CommonServiceFactory commonServiceFactory;

	protected ModelAndView invoke(ZValue paramVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		ParameterContext paramCtx = setProperty(request, response, paramVO, model);
		String programId = getProgramId(paramCtx);
		paramVO.putObject(ConstantsRequest.PROGRAM_ID, programId);
		CommonService service = commonServiceFactory.getService(paramCtx);

		doInvoke(service, paramCtx);

		ModelAndViewResolver mvr = commonServiceFactory.getModelAndViewResolver(paramCtx);
		return mvr.getModelAndView(paramCtx);
	}

	protected void doInvoke(CommonService service, ParameterContext paramCtx) throws Exception{
		paramCtx.setTargetMethod(getTargetMethod(paramCtx));
		CommonListener listener = service.getListener();
		if ( listener != null ) {
			listener.before(paramCtx);
		}

		String targetMethod = getTargetMethod(paramCtx);
		String methodKey = service.getClass().getName() + "." + targetMethod;
		log.debug("{} is invoking", methodKey);
		Method method = methodCache.get(methodKey);
		if (method == null) {
			log.debug("{} key is not in Cache", methodKey);
			method = ReflectionUtils.findMethod(service.getClass(), targetMethod, ParameterContext.class);
			methodCache.put(methodKey, method);
		}

		ReflectionUtils.invokeMethod(method, service, new Object[]{paramCtx});

		if ( listener != null ) {
			listener.after(paramCtx);
		}
	}

	@SuppressWarnings("unchecked")
	protected ParameterContext setProperty(HttpServletRequest request, HttpServletResponse response, ZValue param, ModelMap model) throws Exception {
		ParameterContext paramCtx = new ParameterContext();
		paramCtx.setParam(param);
		paramCtx.setRequest(request);
		paramCtx.setResponse(response);
		paramCtx.setModel(model);
		paramCtx.setUserIp(request.getRemoteAddr());

		if (request.getAttribute(ConstantsRequest.MULTIPART_FILE_MAP) != null) {
			paramCtx.setFiles((Map<String,MultipartFile>)request.getAttribute(ConstantsRequest.MULTIPART_FILE_MAP));
			request.removeAttribute(ConstantsRequest.MULTIPART_FILE_MAP);
		}

		String siteId = param.getString(ConstantsRequest.SITE_ID);
		if (!StringUtils.isNullOrEmpty(siteId) ) {
			siteId = StrUtils.split(request.getRequestURI(), "/")[1];
			param.putObject(ConstantsRequest.SITE_ID, siteId);
		}
		param.put(ConstantsRequest.REQUEST_URI, request.getRequestURI());
		return paramCtx;
	}

	protected abstract String getProgramId(ParameterContext paramCtx);

	protected abstract String getTargetMethod(ParameterContext paramCtx);
}
