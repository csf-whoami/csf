package com.csf.base.handler;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.csf.base.ParameterContext;
import com.csf.base.core.ZValue;
import com.csf.base.dao.ISqlDAO;
import com.csf.base.paging.PaginationInfo;
import com.csf.base.service.file.IFileMngService;
import com.csf.base.vo.QueryIdVO;

public abstract class ListHandler implements Handler {

	protected ISqlDAO<ZValue> sqlDao;

	protected IFileMngService fileMngService;

	@Override
	public boolean invoke(ParameterContext paramCtx) throws Exception {
		ZValue param = paramCtx.getParam();
		ModelMap model = paramCtx.getModel();
		long totCnt = getListCount(paramCtx);
		model.addAttribute("resultCnt", totCnt);

		PaginationInfo paginationInfo = getPaginationInfo(param);
		paginationInfo.setTotalRecordCount((int)totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		setPagingParam(paginationInfo, param);

		List<ZValue> resultList = getResultList(paramCtx);
		model.addAttribute("resultList", resultList);

		Map<String, List<ZValue>> fileMap = getFileMap(param, resultList);
		model.addAttribute("fileMap", fileMap);

		fileMngService.setFirstProperties(resultList, fileMap);

		String pageNav = getPageNavigateString(paramCtx);
		model.addAttribute("pageNav", pageNav);

    	return CONTINUE;
	}

	public abstract Map<String, List<ZValue>> getFileMap(ZValue param, List<ZValue> resultList) throws Exception;

	protected abstract PaginationInfo getPaginationInfo(ZValue param);

	protected abstract void setPagingParam(PaginationInfo paginationInfo, ZValue param) throws Exception;

	protected List<ZValue> getResultList(ParameterContext paramCtx) throws Exception{
		ISqlDAO<ZValue> vSqlDao = paramCtx.getSqlDAO();
		if (paramCtx.getSqlDAO() != null) {
			return vSqlDao.findAll(paramCtx.getQueryIdVO().getListQueryId(), paramCtx.getParam());
		}
		else {
			return sqlDao.findAll(paramCtx.getQueryIdVO().getListQueryId(), paramCtx.getParam());
		}

	}

	protected long getListCount(ParameterContext paramCtx) throws Exception{
		QueryIdVO qv = paramCtx.getQueryIdVO();
		ISqlDAO<ZValue> vSqlDao = paramCtx.getSqlDAO();
		if (paramCtx.getSqlDAO() != null) {
			return vSqlDao.count(qv.getCountQueryId(), paramCtx.getParam());
		}
		else {
			return sqlDao.count(qv.getCountQueryId(), paramCtx.getParam());
		}
	}

	protected abstract String getPageNavigateString(ParameterContext paramCtx) throws Exception;

	public ISqlDAO<ZValue> getSqlDao() {
		return sqlDao;
	}

	public void setSqlDao(ISqlDAO<ZValue> sqlDao) {
		this.sqlDao = sqlDao;
	}
}
