package com.csf.base.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.csf.base.core.dao.ISqlDAO;
import com.csf.base.core.domain.AclParamVO;
import com.csf.base.core.domain.CacheVO;
import com.csf.base.core.domain.QueryIdVO;
import com.csf.base.core.domain.UsersVO;
import com.csf.base.core.paging.IPageInfo;
import com.csf.base.core.paging.PageQuery;

public class ParameterContext {

	private HttpServletRequest request;
	private HttpServletResponse response;
	Map<String, MultipartFile> files;
	private ModelMap model;
	private ZValue param;
	private Object pkValue;
	private String userIp;
	private QueryIdVO queryIdVO;
	private IPageInfo pageInfo;
	private PageQuery pageQuery;
	private String pageQueryData;
	private UsersVO usersVO;
	private CacheVO cacheVO;
	private String targetMethod;
	private AclParamVO aclParamVO;
/*	private RedirectAttributes redirectAttr;*/
	private ISqlDAO<ZValue> sqlDAO;
	private String upFileTp = "";

	public Object getId() {
		return getPkValue();
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public HttpSession getSession() {
		return getRequest().getSession();
	}
	public ModelMap getModel() {
		return model;
	}
	public void setModel(ModelMap model) {
		this.model = model;
	}
	public ZValue getParam() {
		return param;
	}
	public void setParam(ZValue param) {
		this.param = param;
	}
	public Map<String, MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(Map<String, MultipartFile> files) {
		this.files = files;
	}
	public Object getPkValue() {
		return pkValue;
	}
	public void setPkValue(Object pkValue) {
		this.pkValue = pkValue;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public QueryIdVO getQueryIdVO() {
		return queryIdVO;
	}
	public void setQueryIdVO(QueryIdVO queryIdVO) {
		this.queryIdVO = queryIdVO;
	}
	public IPageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(IPageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public PageQuery getPageQuery() {
		return pageQuery;
	}
	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}
	
	public String getPageQueryData() {
		return pageQueryData;
	}
	public void setPageQueryData(String pageQueryData) {
		this.pageQueryData = pageQueryData;
	}
	public UsersVO getUsersVO() {
		return usersVO;
	}
	public void setUsersVO(UsersVO usersVO) {
		this.usersVO = usersVO;
	}
	public CacheVO getCacheVO() {
		return cacheVO;
	}
	public void setCacheVO(CacheVO cacheVO) {
		this.cacheVO = cacheVO;
	}
	public String getTargetMethod() {
		return targetMethod;
	}
	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}
	public AclParamVO getAclParamVO() {
		return aclParamVO;
	}
	public void setAclParamVO(AclParamVO aclParamVO) {
		this.aclParamVO = aclParamVO;
	}
	public ISqlDAO<ZValue> getSqlDAO() {
		return sqlDAO;
	}
	public void setSqlDAO(ISqlDAO<ZValue> sqlDAO) {
		this.sqlDAO = sqlDAO;
	}
	public String getUpFileTp() {
		return upFileTp;
	}
	public void setUpFileTp(String upFileTp) {
		this.upFileTp = upFileTp;
	}
/*	public RedirectAttributes getRedirectAttr() {
		return redirectAttr;
	}
	public void setRedirectAttr(RedirectAttributes redirectAttr) {
		this.redirectAttr = redirectAttr;
	}*/
}
