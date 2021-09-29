package com.csf.whoami.config;

import java.io.IOException;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.csf.base.core.ZValue;
import com.csf.base.exception.HttpStatus;
import com.csf.whoami.base.constant.ConstantsRequest;
import com.csf.whoami.base.exception.CustomError;
import com.csf.whoami.base.exception.ResponseDataAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		chain.doFilter(new SecurityFilterRequestWrapper(req), res);

		if (req.getServletPath().length() > 1) {
			String admRegex = "^\\/\\w{3}\\/.*$";
			Pattern checkRegex = Pattern.compile(admRegex);
			Matcher regexMatcher = checkRegex.matcher(req.getServletPath());
			if (regexMatcher.matches()) {
				ZValue urlParams = fetchRequest(req);
				if (urlParams == null) {
					SecurityContextHolder.clearContext();
					ResponseDataAPI errorResponse = ResponseDataAPI.build();
					errorResponse.setSuccess(false);
					errorResponse.setError(Collections.singletonList(new CustomError(null, "S002", "Error happen")));
					res.setStatus(HttpStatus.BAD_REQUEST.value());
					res.setContentType("application/json");
					res.getWriter().write(convertObjectToJson(errorResponse));
					return;
				}
//				String path = urlParams.getString(ConstantsRequest.TARGET_METHOD);
//				RequestDispatcher rd = req.getRequestDispatcher(path);
//				rd.forward(req, res);
				
				ServletContext context = getServletContext();
				RequestDispatcher nameRD =
						  context.getNamedDispatcher(ConstantsRequest.TARGET_METHOD);
				nameRD.forward(req, res);

			}
		}

//		String token = tokenProvider.resolveToken(req);
//		try {
//			if (token != null && tokenProvider.validateToken(token)) {
//				Authentication auth = tokenProvider.getAuthentication(token);
//				SecurityContextHolder.getContext().setAuthentication(auth);
//			}
//		} catch (CustomException ex) {
//			SecurityContextHolder.clearContext();
//			ResponseDataAPI errorResponse = ResponseDataAPI.build();
//			errorResponse.setSuccess(false);
//			errorResponse.setError(Collections.singletonList(new CustomError(null, "S002", ex.getMessage())));
//			res.setStatus(ex.getHttpStatus().value());
//			res.setContentType("application/json");
//			res.getWriter().write(convertObjectToJson(errorResponse));
//			return;
//		}

//		chain.doFilter(req, res);
	}

	private ZValue fetchRequest(HttpServletRequest request) {
		String urlPath = request.getServletPath();
		String sysId = urlPath.substring(1, 4);

		if("adm".equals(sysId)) {
			return fetchAdmRequest(request);
		}
		if("api".equals(sysId)) {
			return fetchApiRequest(request);
		}
		if("web".equals(sysId)) {
			return fetchWebRequest(request);
		}

//		EProgramType type = EProgramType.valueOf(sysId.toUpperCase());
//		switch (type) {
//		case ADMIN:
//			return fetchAdmRequest(request);
//		case API:
//			return fetchApiRequest(request);
//		case WEB:
//			return fetchWebRequest(request);
//		default:
//			requestParams = null;
//			break;
//		}

		return null;
	}

	private ZValue fetchWebRequest(HttpServletRequest request) {
		return null;
	}

	private ZValue fetchApiRequest(HttpServletRequest request) {
		return null;
	}

	private ZValue fetchAdmRequest(HttpServletRequest request) {

		ZValue requestParam = new ZValue();
		String urlPath = request.getServletPath();
		String pattern = "^\\/(\\w{3})\\/(\\w{3})\\/(\\w)\\/(\\w+)\\/(\\w+)\\/(\\w[\\w|-]+).html.*$";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(urlPath);
		if (m.find()) {
			String siteId = m.group(1);
			String compId = m.group(2);
			String appId = m.group(3);
			String pakageId = m.group(4);
			String programId = m.group(5);
			String targetMethod = m.group(6);

			requestParam.put(ConstantsRequest.SITE_ID, siteId);
			requestParam.put(ConstantsRequest.COMP_ID, compId);
			requestParam.put(ConstantsRequest.APP_ID, appId);
			requestParam.put(ConstantsRequest.PACKAGE_ID, pakageId);
			requestParam.put(ConstantsRequest.PROGRAM_ID, programId);
			requestParam.put(ConstantsRequest.TARGET_METHOD, targetMethod);
			return requestParam;
		}

		return null;
	}

	/**
	 * @author Tuan
	 * @param object
	 * @return String
	 * @throws JsonProcessingException
	 * @description convert object to Json
	 */
	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}