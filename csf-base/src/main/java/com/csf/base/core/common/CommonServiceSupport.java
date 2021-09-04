package com.csf.base.core.common;

import com.csf.base.core.ZValue;
import com.csf.base.core.dao.ISqlDAO;
import com.csf.base.core.paging.PageQuery;

/**
 * CRUD기능이 필요없는 서비스는 이 클래스를 확장한다.
 * @author khd
 *
 */
public class CommonServiceSupport implements CommonService {

	private CommonListener listener;
	private String pageQueryData;
	protected PageQuery pageQuery;
	protected GlobalsProperties globalsProperties;
	
	/**
	 * 디비쿼리 실행 dao
	 * sqlDao를 명세하지않는 경우 'SqlDAO'빈으로 초기화
	 */
	protected ISqlDAO<ZValue> sqlDao;
	
	@Override
	public CommonListener getListener() {
		return listener;
	}
	
	public void setListener(CommonListener listener) {
		this.listener = listener;
	}

	@Override
	public String getPageQueryData() {
		return pageQueryData;
	}

	public void setPageQueryData(String pageQueryData) {
		this.pageQueryData = pageQueryData;
	}

	@Override
	public PageQuery getPageQuery() {
		return pageQuery;
	}
	
	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}

	@Override
	public GlobalsProperties getGlobalsProperties() {
		return globalsProperties;
	}

	public void setGlobalsProperties(GlobalsProperties globalsProperties) {
		this.globalsProperties = globalsProperties;
	}

	public <S> S getSqlDao(Class<S> clazz) {
		try {
	        return clazz.cast(sqlDao);
	    } catch(ClassCastException e) {
	    	e.printStackTrace();
	        return null;
	    }
	}
	
	public ISqlDAO<ZValue> getSqlDao() {
		return this.sqlDao;
	}
	
	public void setSqlDao(ISqlDAO<ZValue> sqlDao) {
		this.sqlDao = sqlDao;
	}
}
