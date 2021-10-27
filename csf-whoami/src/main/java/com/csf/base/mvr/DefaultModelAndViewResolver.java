package com.csf.base.mvr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.csf.base.ParameterContext;
import com.csf.base.core.ZValue;
import com.csf.base.utilities.StringUtils;

public class DefaultModelAndViewResolver extends UriModelAndViewResolver {

	//어떠한 게시판에서 행동을 마친후에 이당하는 URL 
	@Override
	public String determineTargetUri(ParameterContext paramCtx) throws Exception {
		ModelMap model = paramCtx.getModel();
		HttpServletRequest request =  paramCtx.getRequest();
		String resultCode = (String)model.get(RESULT_CODE_KEY);
		if( resultCode == null ) {
			return null;
		}
		ZValue param = paramCtx.getParam();
		String bbsId = param.getString("bbsId");
		StringBuffer result = new StringBuffer();
		String pageQueryString = param.getString("pageQueryString");
		String contextPath = request.getContextPath();//컨텍스트패스

		if( StringUtils.hasLength(pageQueryString) ) pageQueryString = StringUtils.replace(pageQueryString, "&amp;", "&");
		else{
			pageQueryString = "menuNo="+param.getString("menuNo");
		}
		if( SUCCESS.equals(resultCode) || FAIL.equals(resultCode) ) {
			result.append(contextPath).append("/bos/bbs/").append(bbsId).append("/list.do?").append(pageQueryString);
		}
		else if( REPLY_SUCCESS.equals(resultCode) ) {
			result.append(contextPath).append("/bos/bbs/").append(bbsId).append("/view.do?nttId=").append(param.getString("parntsNo")).append("&").append(pageQueryString);
		}
		else if( MODIFY_FAIL.equals(resultCode) ) {
			result.append(contextPath).append("/bos/bbs/").append(bbsId).append("/forUpdate.do?nttId=").append(param.getString("nttId")).append("&").append(pageQueryString);
		}
		else if( ERROR.equals(resultCode)  ) {
			result.append("javascript:history.back();");
		}
		return result.toString();
	}

}
