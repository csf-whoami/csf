package com.csf.base.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.csf.base.ParameterContext;
import com.csf.base.constant.ConstantsRequest;
import com.csf.base.core.ZValue;
import com.csf.base.dao.BaseRepository;
import com.csf.base.paging.PaginationInfo;
import com.csf.base.utilities.StringUtils;
import com.csf.base.vo.QueryIdVO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class ListHandler implements Handler {

	// Common Repository.
	@Autowired
	private BaseRepository sqlDao;

//	@Autowired
//	protected IFileMngService fileMngService;

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

//		Map<String, List<ZValue>> fileMap = getFileMap(param, resultList);
//		model.addAttribute("fileMap", fileMap);

//		fileMngService.setFirstProperties(resultList, fileMap);

//		String pageNav = getPageNavigateString(paramCtx);
//		model.addAttribute("pageNav", pageNav);

		return CONTINUE;
	}

	public abstract Map<String, List<ZValue>> getFileMap(ZValue param, List<ZValue> resultList) throws Exception;

	protected abstract PaginationInfo getPaginationInfo(ZValue param);

	protected abstract void setPagingParam(PaginationInfo paginationInfo, ZValue param) throws Exception;

	/**
	 * Get list result.
	 * 
	 * @param paramCtx
	 * @return
	 * @throws Exception
	 */
	protected List<ZValue> getResultList(ParameterContext paramCtx) throws Exception{
		String listQueryId = paramCtx.getQueryIdVO().getListQueryId();
		if(StringUtils.isNullOrEmpty(listQueryId)) {
			String compId = paramCtx.getParam().getString(ConstantsRequest.COMP_ID).toUpperCase();
			String programId = paramCtx.getParam().getString(ConstantsRequest.PROGRAM_ID);
			String methodId = paramCtx.getParam().getString(ConstantsRequest.TARGET_METHOD);
			listQueryId = compId + StringUtils.toName(programId) + StringUtils.toName(methodId);
		}
		// Change DAO to mapper.
		BaseRepository vSqlDao = paramCtx.getSqlDAO();
		if (paramCtx.getSqlDAO() != null) {
			return vSqlDao.findAll(listQueryId, paramCtx.getParam());
		} else {
			return sqlDao.findAll(listQueryId, paramCtx.getParam());
		}
	}

	/**
	 * Get total record in List page.
	 * 
	 * @param paramCtx
	 * @return
	 * @throws Exception
	 */
	protected long getListCount(ParameterContext paramCtx) throws Exception{
		QueryIdVO qv = paramCtx.getQueryIdVO();
		BaseRepository vSqlDao = paramCtx.getSqlDAO();
		if (paramCtx.getSqlDAO() != null) {
			return vSqlDao.count(qv.getCountQueryId(), paramCtx.getParam());
		} else {
			return sqlDao.count(qv.getCountQueryId(), paramCtx.getParam());
		}
	}

	protected abstract String getPageNavigateString(ParameterContext paramCtx) throws Exception;
}
