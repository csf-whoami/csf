package com.csf.base.service.common;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;

import com.csf.base.ParameterContext;
import com.csf.base.constant.ConstantsRequest;
import com.csf.base.core.ZValue;
import com.csf.base.mvr.ModelAndViewResolver;

import lombok.Getter;
import lombok.Setter;

//@Service
@Getter @Setter
public class DefaultCommonServiceFactory implements CommonServiceFactory, ApplicationContextAware {

	Logger log = LoggerFactory.getLogger(this.getClass());

	protected ApplicationContext applicationContext;
	protected Map<String, String> serviceNameMap;
	protected Map<String, String> mvrNameMap;
	protected Map<String, String> validatorNameMap;

	@Override
	public CommonService getService(ParameterContext paramCtx) throws Exception {
		ZValue param = paramCtx.getParam();
		String programId = param.getString(ConstantsRequest.PROGRAM_ID);
		// TODO: Move to runner App
		if (CollectionUtils.isEmpty(serviceNameMap)) {
			serviceNameMap = new HashMap<>();
		}
		String targetName = serviceNameMap.get(programId);
		//targetName이 없는경우 programId + Service
		//ex) programId가 siteMng일경우 서비스명은 siteInfoService
		if ( targetName == null ) {
			targetName = programId + "Service";
		}
		param.putObject(ConstantsRequest.TARGET_NAME, targetName);

		if ( log.isDebugEnabled() ) {
			log.debug("real bean is [" + targetName + "]");
		}
		try {
			return (CommonService)applicationContext.getBean(targetName);
		} catch (Exception e) {
			log.debug("Error!!!!! Could not find "+targetName+".");
			throw new FileNotFoundException();
		}
	}

	@Override
	public ModelAndViewResolver getModelAndViewResolver(ParameterContext paramCtx) throws Exception {
		ZValue param = paramCtx.getParam();
		String programId = param.getString(ConstantsRequest.PROGRAM_ID);
		// TODO: Move to runner App
//		if(mvrNameMap == null) {
//			mvrNameMap = new HashMap<>();
//			mvrNameMap.put("default", "defaultModelAndViewResolver");
//		}
		if(mvrNameMap != null) {
			String targetName = mvrNameMap.get(programId);
			//targetName이 없는경우 programId + ProgramService
			//ex) programId가 siteMng일경우 서비스명은 siteMngProgramService
			if ( targetName == null ) {
				targetName = mvrNameMap.get(DEFAULT_KEY);
			}
			if ( log.isDebugEnabled() ){
				log.debug("real bean is [" + targetName + "]");
			}
			return (ModelAndViewResolver)applicationContext.getBean(targetName);
		}
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		this.applicationContext = applicationContext;
	}
}
