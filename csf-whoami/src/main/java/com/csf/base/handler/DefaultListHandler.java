package com.csf.base.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.csf.base.ParameterContext;
import com.csf.base.core.ZValue;
import com.csf.base.paging.PaginationInfo;
import com.csf.base.service.common.CommonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultListHandler extends ListHandler {
	@Override
	protected PaginationInfo getPaginationInfo(ZValue param) {
		setPageSize(param);
		int pageUnit = param.getInt("pageUnit", 10);
		int pageSize = param.getInt("pageSize", 10);
		int pageIndex = param.getInt("pageIndex", 1);

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(pageUnit);
		paginationInfo.setPageSize(pageSize);
		return paginationInfo;
	}

	@Override
	protected void setPagingParam(PaginationInfo paginationInfo, ZValue param) throws Exception {
		int pageIndex = param.getInt("pageIndex", 1);
		param.put("pageIndex", pageIndex);
		param.put("firstIndex", paginationInfo.getFirstRecordIndex());
		param.put("lastIndex", paginationInfo.getLastRecordIndex());
		param.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
		param.put("totCnt", paginationInfo.getTotalRecordCount());
	}

	@Override
	protected String getPageNavigateString(ParameterContext paramCtx) throws Exception {
		ZValue param = paramCtx.getParam();
		setPageSize(param);
		int pageUnit = param.getInt("pageUnit", 10);
		int pageSize = param.getInt("pageSize", 10);
		int pageIndex = param.getInt("pageIndex", 1);
		int totCnt = param.getInt("totCnt");

		HttpServletRequest request = paramCtx.getRequest();
		String link = param.getString(CommonService.PAGE_LINK);
		if( !StringUtils.hasText(link) ){
			link = request.getRequestURI();
			String pageLinkQueryString = paramCtx.getPageQuery().getPageLinkQueryString(param, paramCtx.getPageQueryData());
			if( pageLinkQueryString != null )
				link += "?" + pageLinkQueryString;
		}
		return paramCtx.getPageInfo().getPageNavString(pageSize, pageUnit, totCnt, pageIndex, link);
	}

	private void setPageSize(ZValue param) {
		int pageUnit = param.getInt("pageUnit");
		if (pageUnit == 0 ) {
			param.put("pageUnit", 10);
		}
		int pageSize = param.getInt("pageSize");
		if ( pageSize == 0 ) {
			param.put("pageSize", 10);
		}
	}

	@Override
	public Map<String, List<ZValue>> getFileMap(ZValue zvl, List<ZValue> resultList) throws Exception {
//		return fileMngService.getFileMap(zvl, resultList);
		return new HashMap<>();
	}
}
