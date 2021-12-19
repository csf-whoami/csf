package com.csf.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.csf.base.constant.ConstantsRequest;
import com.csf.base.dao.BaseRepository;
import com.csf.base.utilities.StringUtils;

@Component
public class ApplicationBeanUtils implements ApplicationContextAware {

	protected static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationBeanUtils.applicationContext = applicationContext;
	}

	public static BaseRepository getProgramDAO() {
//		return BeanUtils.instantiateClass(clazz);
		
		boolean checked = false;
		String currentClass = ApplicationBeanUtils.class.getName();
		String serviceClass = "";

		for(StackTraceElement element : Thread.currentThread().getStackTrace()) {
			if(!currentClass.equals(element.getClassName()) && element.getClassName().contains(ConstantsRequest.SERVICE_NM)) {
				String callerClass = element.getClassName();
				String pattern = "\\.([^.\\\\]+)"+ConstantsRequest.SERVICE_NM+"(Impl?)$";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(callerClass);
				if (m.find()) {
					serviceClass = m.group(1);
					checked = true;
				}
			}
			if(checked) {
				break;
			}
		}
		String repositoryNm = StringUtils.toFirstLower(serviceClass) + ConstantsRequest.REPOSITORY_NM;
		Object programRepository = applicationContext.getBean(repositoryNm);
		if(programRepository != null) {
			return (BaseRepository)programRepository;
		}
		BaseRepository baseRepository = (BaseRepository) applicationContext.getBean(BaseRepository.class.getName());
		return baseRepository;
	}
}
