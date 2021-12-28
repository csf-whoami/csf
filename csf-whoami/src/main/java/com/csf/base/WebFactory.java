package com.csf.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerMapping;

import com.csf.base.constant.ConstantsRequest;
import com.csf.base.core.ZValue;

public class WebFactory {

	protected Log log = LogFactory.getLog(WebFactory.class);

	public static ZValue getAttributes(HttpServletRequest request) {
		ZValue zvl = new ZValue();
		Enumeration<?> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()){
			String key = (String) enumeration.nextElement();
			String[] values = request.getParameterValues(key);
			if(values!=null){
				zvl.putObject(key, (values.length > 1) ? values:values[0] );
				if (values.length > 1) {
					zvl.putObject(key + "Arr", values);
				}
			}
		}
		// Setting parameter
		Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		for (Object key : pathVariables.keySet()) {
			zvl.put(key, pathVariables.get(key));
		}
		String admRegex = "^\\/\\w{3}\\/.*$";
		Pattern checkRegex = Pattern.compile(admRegex);
		Matcher regexMatcher = checkRegex.matcher(request.getServletPath());
		if (regexMatcher.matches()) {
			String urlPath = request.getServletPath();
			zvl.put(ConstantsRequest.SITE_ID, urlPath.substring(1, 4));
		}
		return zvl;
	}

	public static String buildUrl(String link, ZValue param, String... paramStrings) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder(link);
		if (ArrayUtils.isNotEmpty(paramStrings)) {
			boolean first = true;
			for (String p : paramStrings) {
				sb.append(first ? "?" : "&");
				sb.append(p);
				sb.append('=');
				sb.append(URLEncoder.encode(param.getString(p), "UTF-8"));
				first = false;
			}
		}
		return sb.toString();
	}

	public static String buildUrl(String link, ZValue param, List<String> paramStrings) throws UnsupportedEncodingException {
		String[] ps = paramStrings.toArray(new String[paramStrings.size()]);
		return buildUrl(link, param, ps);
	}

	public static boolean isMobile(HttpServletRequest request) {
		boolean isMobile = false;
		String userAgent = request.getHeader("user-agent");
		boolean mobile1 = userAgent.matches(
				".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mni|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
		boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*");
		if (mobile1 || mobile2) {
			isMobile = true;
		}
		else {
			isMobile = false;
		}
		return isMobile;
	}
	
	public static String setParam(String name, String delim, int count, ZValue param) {
		String value = param.getString(name);
		if (!StringUtils.hasText(value)) {
			for (int i=1; i<=count; i++) {
				if (i == count) {
					value += param.getString(name+i);
				}
				else {
					value += param.getString(name+i) + delim;
				}
			}
			param.putObject(name, value);
		}
		return value;
	}
	
	public static void setUserInfoParam(ZValue param) throws Exception {
//		UsersVO user = (UsersVO)UnpUserDetailsHelper.getAuthenticatedUser();
//		if (user != null) {
//			param.put("registId", user.getUserId());
//			param.put("updtId", user.getUserId());
//			param.put("userPin", user.getUserPin());
//		}
	}
}
