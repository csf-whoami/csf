package com.csf.base;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.core.ParameterContext;
import com.csf.base.core.ZValue;
import com.csf.base.core.common.CommonListener;
import com.csf.base.core.common.CommonService;
import com.csf.base.core.common.CommonServiceFactory;
import com.csf.base.core.common.ModelAndViewResolver;
import com.csf.base.core.common.StrUtils;
import com.csf.base.core.paging.PageQuery;
import com.csf.base.core.util.UnpCollectionUtils;

public abstract class AbstractCommonController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private Map<String, Method> methodCache = UnpCollectionUtils.lruCache(1000);
	/**
	 * 키값인 programId로 실제 구현 서비스와 뷰값을 가져오기 위한 팩토리객체
	 */
    protected CommonServiceFactory commonServiceFactory;

	public ModelAndView invoke(ZValue paramVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		ParameterContext paramCtx = setProperty(request, response, paramVO, model);
		String programId = getProgramId(paramCtx);
		paramVO.putObject(CommonService.PROGRAM_ID, programId);
		CommonService service = commonServiceFactory.getService(paramCtx);

		doInvoke(service, paramCtx);

		//페이징 파라미터
		PageQuery pageQuery = service.getPageQuery();
		if (pageQuery != null) {
			String pageQueryData = service.getPageQueryData();
			String pageQueryString = pageQuery.getPageQueryString(paramVO, pageQueryData);
			model.addAttribute(CommonService.PAGE_QUERY_STRING, pageQueryString);
		}

		ModelAndViewResolver mvr = commonServiceFactory.getModelAndViewResolver(paramCtx);

    	return mvr.getModelAndView(paramCtx);
	}

	protected void doInvoke(CommonService service, ParameterContext paramCtx) throws Exception{
		paramCtx.setTargetMethod(getTargetMethod(paramCtx));
		//메소드 실행하기전 등록된 리스너 실행 CompositeCommonListener 인경우 리스너순차적실행
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

		//메소드 실행후 리스너 실행 CompositeCommonListener경우 역순으로 실행
		if ( listener != null ) {
			listener.after(paramCtx);
		}
	}

	protected ParameterContext setProperty(HttpServletRequest request, HttpServletResponse response, ZValue param, ModelMap model) throws Exception{
		ParameterContext paramCtx = new ParameterContext();
		paramCtx.setParam(param);
		paramCtx.setRequest(request);
		paramCtx.setResponse(response);
		paramCtx.setModel(model);
		paramCtx.setUserIp(request.getRemoteAddr());

		/*
		if ( request instanceof MultipartHttpServletRequest ) {
			paramCtx.setFiles(((MultipartHttpServletRequest)request).getFileMap());
		}
		*/
		if (request.getAttribute("multipartFileMap") != null) {
			paramCtx.setFiles((Map<String,MultipartFile>)request.getAttribute("multipartFileMap"));
			request.removeAttribute("multipartFileMap");
		}

		String siteId = param.getString(CommonService.SITE_ID);
		if ( !StringUtils.hasText(siteId) ) {
			siteId = StrUtils.split(request.getRequestURI(), "/")[1];
			param.putObject(CommonService.SITE_ID, siteId);
		}
		param.put("requestURI", request.getRequestURI());
		return paramCtx;
	}

	public CommonServiceFactory getCommonServiceFactory() {
		return commonServiceFactory;
	}

	public void setCommonServiceFactory(CommonServiceFactory commonServiceFactory) {
		this.commonServiceFactory = commonServiceFactory;
	}

	/**
	 * 실제구현 서비스를 찾기위한 키값가져오기
	 * @param paramCtx HttpServletRequest, HttpServletResponse, Model, Parameter등의 정보를 담고있다.
	 * @return 구현서비스를 찾을키값
	 */
	protected abstract String getProgramId(ParameterContext paramCtx);

	/**
	 * 실제구현 서비스의 메소드명 가져오기
	 * @param paramCtx HttpServletRequest, HttpServletResponse, Model, Parameter등의 정보를 담고있다.
	 * @return 구현서비스 실행 메소드
	 */
	protected abstract String getTargetMethod(ParameterContext paramCtx);
}
