package com.csf.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.csf.base.common.Repository;
import com.csf.base.constant.ConstantsCommon;
import com.csf.base.utilities.StringUtils;

@Aspect
@Component
public class AttributeRequestAspect {

	@Pointcut("execution(* com.csf.base.common.BaseController.*(..)) && args(request,..)")
	public void execute(HttpServletRequest request) {}

	@Before("execute(request)")
	public void addAttributeRequest(JoinPoint pjp, HttpServletRequest request) throws Throwable {

		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

		String serverName = request.getServerName().toLowerCase();
	
		String isDevEnv = ConstantsCommon.FALSE;
		if ("localhost".equals(serverName) || serverName.endsWith(".com.csf")) {
			isDevEnv = ConstantsCommon.TRUE;
		}
		request.setAttribute("isDevEnv", isDevEnv);
	
		request.setAttribute("confHeader", Repository.getConfig("config.menu.position"));

		request.setAttribute("contextPath", request.getContextPath());

		String httpcontext = request.getRequestURL().toString();
		httpcontext = httpcontext.substring(0,(httpcontext.indexOf(request.getContextPath())+request.getContextPath().length()));
		String protocol = Repository.getConfig("config.protocol");
		if (protocol != null && !protocol.equals("")) {
			httpcontext = protocol + httpcontext.substring(httpcontext.indexOf(":"));
		}
		
		String applySSL = Repository.getConfig("config.ht.activex.applyssl");
		String serverPort = String.valueOf(request.getServerPort()); //"80"
		if (httpcontext.toLowerCase().startsWith("https")) {
			applySSL = ConstantsCommon.TRUE;
			serverPort = "443";
		}
		request.setAttribute("serverName", request.getServerName());
		request.setAttribute("serverPort", serverPort);
		request.setAttribute("applySSL", applySSL);

		request.setAttribute("classid", Repository.getConfig("config.ht.activex.classid"));
		//CODEBASE���擾
		String codebase = Repository.getConfig("config.ht.activex.codebase");
		if (codebase != null && !codebase.equals("")) {
			if (!codebase.toLowerCase().startsWith("http")) {//���΃p�X�Ȃ�
				codebase = httpcontext + codebase;
			}
		}
		request.setAttribute("codebase", codebase);
		setButsuriSokoCode2Cookie(request, response);
	}

	private void setButsuriSokoCode2Cookie(HttpServletRequest request, HttpServletResponse response) {

		Enumeration<String> enumeration = request.getParameterNames();

		String butsuriSokoCodeValue = null;
		boolean duplicateFlag = false;
		boolean matchFlag = false;

		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();

			String lowerStr = key.toLowerCase(request.getLocale());

			if (lowerStr.equals("butsurisokocode")) {
				butsuriSokoCodeValue = request.getParameter(key);
				matchFlag = true;
				break;

			} else if (lowerStr.indexOf("butsurisokocode") != -1) {
				if (lowerStr.indexOf('[') == -1 && lowerStr.indexOf(']') == -1) {

					if (!StringUtils.isNullOrEmpty(butsuriSokoCodeValue)) {
						duplicateFlag = true;
					}
					butsuriSokoCodeValue = request.getParameter(key);
				}
			}
		}

//		if (matchFlag && !StringUtils.isNullOrEmpty(butsuriSokoCodeValue)) {
//			writeCookie(butsuriSokoCodeValue, request, response);
//		} else {
//			if (!duplicateFlag && !StringUtils.isNullOrEmpty(butsuriSokoCodeValue)) {
//				writeCookie(butsuriSokoCodeValue, request, response);
//			}
//		}
	}

//	private void writeCookie(String butsuriSokoCode, HttpServletRequest request, HttpServletResponse response) {
//		String cookieName = "searchButsuriSokoCode";
//
//		String cookieValue = CookieUtil.getCookieValue(cookieName, request, response);
//
//		if (StringUtils.isNullOrEmpty(butsuriSokoCode) || butsuriSokoCode.equals(cookieValue)) {
//			return;
//		}
//
//		User user = (User) request.getSession().getAttribute(User.USER_INFO);
//		if (user == null) {
//			return;
//		}
//
//		try {
//
//			RBSOKP1Table rbsokp1 = new RBSOKP1Table(ExecuteRepositoryFactory.getInstance().getRepository(), user);
//
//			rbsokp1.setPrimaryValue(user.getSokoCode(), butsuriSokoCode);
//
//			if (rbsokp1.refer()) {
//				CookieUtil.writeCookie(cookieName, butsuriSokoCode, request, response);
//			}
//
//		} catch (SQLException e) {
//		}
//	}
}
