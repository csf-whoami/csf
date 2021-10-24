package com.csf.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.csf.base.core.ZValue;

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
	private String pageQueryData;
//	private UsersVO usersVO;
//	private CacheVO cacheVO;
	private String targetMethod;
	private String viewPage;
//	private ISqlDAO<ZValue> sqlDAO;
	private String upFileTp = "";
}
