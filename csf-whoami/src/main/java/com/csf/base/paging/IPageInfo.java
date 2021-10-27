package com.csf.base.paging;

public interface IPageInfo
{
	public String getPageNavString( int pageSize, int total, int cpage, String link );

	public String getPageNavString( int pageSize, int pageUnit, int total, int cpage, String link );

	public String getPageNavStringToFunc( int pageSize, int total, int cpage, String funcName );
}
