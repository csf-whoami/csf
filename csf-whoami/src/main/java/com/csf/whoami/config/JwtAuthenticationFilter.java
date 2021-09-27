package com.csf.whoami.config;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.csf.base.exception.HttpStatus;
import com.csf.whoami.base.exception.CustomError;
import com.csf.whoami.base.exception.CustomException;
import com.csf.whoami.base.exception.EProgramType;
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
				Map<String, String> urlParams = fetchRequest(req);
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
			}
		}

		String token = tokenProvider.resolveToken(req);
		try {
			if (token != null && tokenProvider.validateToken(token)) {
				Authentication auth = tokenProvider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (CustomException ex) {
			SecurityContextHolder.clearContext();
			ResponseDataAPI errorResponse = ResponseDataAPI.build();
			errorResponse.setSuccess(false);
			errorResponse.setError(Collections.singletonList(new CustomError(null, "S002", ex.getMessage())));
			res.setStatus(ex.getHttpStatus().value());
			res.setContentType("application/json");
			res.getWriter().write(convertObjectToJson(errorResponse));
			return;
		}

		chain.doFilter(req, res);
	}

	private Map<String, String> fetchRequest(HttpServletRequest request) {
		String urlPath = request.getServletPath();
		String sysId = urlPath.substring(1, 4);
		Map<String, String> requestParams = new HashMap<>();

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

		return requestParams;
	}

	private Map<String, String> fetchWebRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<String, String> fetchApiRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<String, String> fetchAdmRequest(HttpServletRequest request) {

		String urlPath = request.getServletPath();
		String pattern = "^\\/(\\w{3})\\/(\\w{3})\\/(\\w)\\/(\\w+)\\/(\\w{4})\\/(\\w+)\\/(\\w[\\w|-]+).html.*$";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(urlPath);
		if (m.find()) {
			String type = m.group(1);
			String pkg = m.group(2);
			String appId = m.group(3);
			String roleNm = m.group(4);
			String cacheKey = m.group(5);
			String programId = m.group(6);
			String method = m.group(7);

			// TODO: Show Detail
			System.out.println("type: " + type);
			System.out.println("pkg: " + pkg);
			System.out.println("appId: " + appId);
			System.out.println("roleNm: " + roleNm);
			System.out.println("cacheKey: " + cacheKey);
			System.out.println("programId: " + programId);
			System.out.println("method: " + method);
		}

//		^\/\w{3}\/(\w+)\/(\w)\/(\w+)\/(\w{4})\/(\w+)\/(\w[\w|-]+).html$
//		String urlPath = request.getServletPath();
//		String admRegex = "\\/(\\w{3})\\/(\\w{3})\\/(\\w).*";
//		Pattern checkRegex = Pattern.compile(admRegex);
//		Matcher regexMatcher = checkRegex.matcher(urlPath);
//		System.out.println("Match:" + regexMatcher.matches());
//		System.out.println("find: " + regexMatcher.find());
//		if (regexMatcher.matches() && regexMatcher.find()) {
//			System.out.println("group(1): " + regexMatcher.group(1));
//			System.out.println("group(2): " + regexMatcher.group(2));
//			System.out.println("group(3): " + regexMatcher.group(3));
//			System.out.println("group(4): " + regexMatcher.group(4));
//			System.out.println("group(5): " + regexMatcher.group(5));
//			System.out.println("group(6): " + regexMatcher.group(6));
//		}

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