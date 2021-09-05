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

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
	private ISqlDAO<ZValue> sqlDAO;
	private String upFileTp = "";

	public Object getId() {
		return getPkValue();
	}
	public HttpSession getSession() {
		return getRequest().getSession();
	}
}
