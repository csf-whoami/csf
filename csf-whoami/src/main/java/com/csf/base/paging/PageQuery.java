package com.csf.base.paging;

import com.csf.base.core.ZValue;

public interface PageQuery {
	
	public String getPageLinkQueryString(ZValue param, String pageQueryData) throws Exception;
	
	public String getPageQueryString(ZValue param, String pageQueryData) throws Exception;
}
