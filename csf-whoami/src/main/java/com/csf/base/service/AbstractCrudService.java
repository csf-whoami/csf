package com.csf.base.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;

import com.csf.base.ParameterContext;
import com.csf.base.constant.ConstantsRequest;
import com.csf.base.core.ZValue;
import com.csf.base.dao.ISqlDAO;
import com.csf.base.handler.ListHandler;
import com.csf.base.paging.IPageInfo;
import com.csf.base.service.common.CommonServiceSupport;
import com.csf.base.service.file.IFileMngService;
import com.csf.base.utilities.StringUtils;
import com.csf.base.vo.QueryIdVO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class AbstractCrudService extends CommonServiceSupport implements CrudService {

	Logger log = LoggerFactory.getLogger(this.getClass());

//	protected IFileMngService fileMngService;

	protected String listQueryId;
	protected String countQueryId;
	protected String viewQueryId;
	protected String excelQueryId;
	@Autowired
	protected ListHandler listHandler;
//	protected IExcelUploadService<ZValue> excelUploadService;

	protected IPageInfo pageInfo;

    protected MessageSource messageSource;

    protected final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");

	public AbstractCrudService(){}
	/**
	 * 페이징이 포함된 일반 게시글의 목록 실제 목록은 ListHandler가 담당한다.
	 * ListHandler로 목록이 아닌 뷰,등록화면에서도 페이징을 가져올수 있다.
	 */
	@Override
	public void list(ParameterContext paramCtx) throws Exception {
		if(paramCtx.getQueryIdVO() == null ){
			QueryIdVO qvo = new QueryIdVO();
			qvo.setListQueryId(listQueryId);
			qvo.setCountQueryId(countQueryId);
			paramCtx.setQueryIdVO(qvo);
		}
		paramCtx.setPageQueryData(getPageQueryData());
		if( paramCtx.getPageQuery() == null ){
			paramCtx.setPageQuery(pageQuery);
		}
		if( paramCtx.getPageInfo() == null ){
			paramCtx.setPageInfo(pageInfo);
		}
		listHandler.invoke(paramCtx);
	}

	@Override
	public void view(ParameterContext paramCtx) throws Exception {
		ZValue param = paramCtx.getParam();
		ModelMap model = paramCtx.getModel();

		ISqlDAO<ZValue> sqlDao = null;
		if (paramCtx.getSqlDAO() != null) sqlDao = paramCtx.getSqlDAO();
		else sqlDao = this.sqlDao;
		ZValue result = sqlDao.findOne(viewQueryId, param);
		model.addAttribute(ConstantsRequest.RESULT, result);

		getFiles(paramCtx);

	}

	protected void getFiles(ParameterContext paramCtx) throws Exception {
		ModelMap model = paramCtx.getModel();
		ZValue result = (ZValue)model.get(ConstantsRequest.RESULT);
		String atchFileId = "";
		if (result != null) atchFileId = result.getString(ConstantsRequest.ATCH_FILE_ID);
		if(StringUtils.hasText(atchFileId)) {
//			List<ZValue> fileList = fileMngService.getFiles(atchFileId);
//			model.addAttribute("fileList", fileList);
//			model.addAttribute("fileListCnt", fileList.size());
//			if (fileList.size() == 1) {
//				model.addAttribute("fileVO", fileList.get(0));
//			}
		}
	}

	@Override
	public void forInsert(ParameterContext paramCtx) throws Exception {
		this.putFileCheckInfo(paramCtx);
	}

	@Override
	public void insert(ParameterContext paramCtx) throws Exception {
		ZValue param = paramCtx.getParam();

//		boolean flag = uploadFile(paramCtx);
//		if (!flag) return;
//
//		ISqlDAO<ZValue> sqlDao = null;
//		if (paramCtx.getSqlDAO() != null) sqlDao = paramCtx.getSqlDAO();
//		else sqlDao = this.sqlDao;
//
//		UsersVO user = (UsersVO)UnpUserDetailsHelper.getAuthenticatedUser();
//		if (user != null) {
//			param.put("updtId", user.getUserId());
//			param.put("registId", user.getUserId());
//		}
//		sqlDao.save(insertQueryId, param);
//		Object pkValue = param.get("unqKey");
//		paramCtx.setPkValue(pkValue);
//		Object result = sqlDao.save(insertQueryId, param);
//		param.put("registRst", result);
//		ModelMap model = paramCtx.getModel();
//		MVUtils.setResultProperty(model, SUCCESS, messageSource.getMessage("success.common.insert", null, Locale.getDefault()));
	}

	@Override
	public void forUpdate(ParameterContext paramCtx) throws Exception{
		ModelMap model = paramCtx.getModel();
		this.putFileCheckInfo(paramCtx);

		model.addAttribute("updateFlag", "Y");
		view(paramCtx);
	}

	@Override
	public void update(ParameterContext paramCtx) throws Exception {
		ZValue param = paramCtx.getParam();

//		boolean flag = uploadFile(paramCtx);
//		if (!flag) return;

//		ISqlDAO<ZValue> sqlDao = null;
//		if (paramCtx.getSqlDAO() != null) sqlDao = paramCtx.getSqlDAO();
//		else sqlDao = this.sqlDao;
//
//		UsersVO user = (UsersVO)UnpUserDetailsHelper.getAuthenticatedUser();
//		if (user != null) {
//			param.put("updtId", user.getUserId());
//			param.put("registId", user.getUserId());
//		}
//		sqlDao.modify(updateQueryId, param);
//		ModelMap model = paramCtx.getModel();
//		MVUtils.setResultProperty(model, SUCCESS, messageSource.getMessage("success.common.update", null, Locale.getDefault()));
	}

//	protected boolean uploadFile(ParameterContext paramCtx) throws Exception {
//		ZValue param = paramCtx.getParam();
//		Map<String, MultipartFile> files = paramCtx.getFiles();
//		if ( files != null && files.size()>0) {
//			fileMngService.upload(files, param);
//		}
//		return true;
//	}

//	protected String getFilePath(ZValue param) throws Exception{
//		String folder = simpleDateFormat.format(new Date());
//		String programId = param.getString(CommonService.PROGRAM_ID);
//		StringBuilder result = new StringBuilder();
//		result.append(globalsProperties.getFileStorePath()).append("/");
//		result.append(programId);
//		result.append("/").append(folder).toString();
//		return result.toString();
//	}

	@Override
	public void delete(ParameterContext paramCtx) throws Exception {
//		ZValue param = paramCtx.getParam();
//		ModelMap model = paramCtx.getModel();
//
//		ISqlDAO<ZValue> sqlDao = null;
//		if (paramCtx.getSqlDAO() != null) sqlDao = paramCtx.getSqlDAO();
//		else sqlDao = this.sqlDao;
//		sqlDao.deleteOne(deleteQueryId, param);
//		MVUtils.setResultProperty(model, SUCCESS, messageSource.getMessage("success.common.delete", null, Locale.getDefault()));
//		model.addAttribute(ModelAndViewResolver.RESULT_CODE_KEY, ModelAndViewResolver.SUCCESS);
	}

	@Override
	public void downloadExcel(ParameterContext paramCtx) throws Exception {
//		ModelMap model = paramCtx.getModel();
//		String[] titles = (String[])model.get("titles");
//		Assert.notNull(titles, "titles must not be null");
//
//		ZValue param = paramCtx.getParam();
//		List<ZValue> excelList = sqlDao.findAll(excelQueryId, param);
//		ExcelGenerateVO vo = new ExcelGenerateVO("data", titles, excelList);
//		model.addAttribute("excel", vo);
	}

	@Override
	public void uploadExcel(ParameterContext paramCtx) throws Exception {
//		ZValue param = paramCtx.getParam();
//		final Map<String, MultipartFile> files = paramCtx.getFiles();
//		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
//		MultipartFile file;
//		while (itr.hasNext()) {
//			Entry<String, MultipartFile> entry = itr.next();
//			file = entry.getValue();
//			String originalFilename = file.getOriginalFilename();
//			if (!"".equals(originalFilename)) {
//				String fileExe = StringUtils.getFilenameExtension(originalFilename);
//				UnpCollectionUtils.setProperty(param, ExeExcelUploadDelegateService.FILE_EXE_PARAM, fileExe);
//				excelUploadService.uploadExcel(param, file.getInputStream());
//			}
//		}
	}

	public void putFileCheckInfo(ParameterContext paramCtx) throws Exception {
//		ZValue param = paramCtx.getParam();
//		String programId = param.getString("programId");
//		String allowFileExt = globalsProperties.getPropertyVal().getString("Service." + programId + ".allowExt");
//		long limitSize = globalsProperties.getPropertyVal().getLong("Service." + programId + ".limitSize");
//		if (!StringUtils.hasText(allowFileExt)) {
//			allowFileExt = globalsProperties.getAllowExt();
//		}
//		if (limitSize==0L) {
//			limitSize = globalsProperties.getLimitFileSize();
//		}
//		param.putObject("allowFileExt", allowFileExt);
//		param.putObject("limitFileSize", limitSize);
//		param.putObject("limitFileSizeFormat", FileUtil.readableFileSize(limitSize));
	}

	@Override
	public void index(ParameterContext paramCtx) throws Exception {
		
	}
}
