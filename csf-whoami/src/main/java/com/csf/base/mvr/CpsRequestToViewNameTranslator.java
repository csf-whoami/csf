package com.csf.base.mvr;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;

public class CpsRequestToViewNameTranslator extends DefaultRequestToViewNameTranslator {

	@Override
	protected String transformPath(String lookupPath) {
		String viewName = super.transformPath(lookupPath);
		StringUtils.replace(viewName, "/auth/", "/");
		return viewName;
	}
}
