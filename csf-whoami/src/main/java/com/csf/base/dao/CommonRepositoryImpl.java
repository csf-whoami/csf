package com.csf.base.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import com.csf.base.constant.ConstantsRequest;
import com.csf.base.core.ZValue;
import com.csf.base.utilities.StringUtils;

@Component
public class CommonRepositoryImpl implements CommonRepository, ApplicationContextAware {

	protected ApplicationContext applicationContext;

	@Override
	public List<ZValue> findAll(String queryId) throws Exception {
		System.out.println("Go to findAll");
		return null;
	}

	@Override
	public List<ZValue> findAll(String queryId, ZValue param) throws Exception {
		return sqlWithParam(queryId, param);
	}

	@Override
	public <ID> List<ZValue> findAllByIds(String queryId, Iterable<ID> ids) throws Exception {
		System.out.println("Go to findAllByIds");
		return null;
	}

	@Override
	public Map<String, List<ZValue>> findAllMap(String queryId, ZValue param, String columnName) throws Exception {
		System.out.println("Go to findAllMap");
		return null;
	}

	@Override
	public <ID> List<ZValue> findById(String queryId, ID id) throws Exception {
		System.out.println("Go to findById");
		return null;
	}

	@Override
	public ZValue findOne(String queryId, ZValue param) throws Exception {
		System.out.println("Go to findOne");
		return null;
	}

	@Override
	public <ID> ZValue findOneById(String queryId, ID id) throws Exception {
		System.out.println("Go to findOneById");
		return null;
	}

	@Override
	public long count(String queryId, ZValue param) throws Exception {
		System.out.println("Go to findOneById");
		return 0;
	}

	@Override
	public String selectString(String queryId, ZValue param) throws Exception {
		System.out.println("Go to selectString");
		return null;
	}

	@Override
	public <ID> int deleteById(String queryId, ID id) throws Exception {
//		log.debug("deleteById===============:{}", queryId);
		//log.debug("id====================:{}", id);
//		return getSqlSession().delete(queryId, id);
		System.out.println("Go to deleteById");
		return 0;
	}

//	private BaseMapper getMapper(String currentMethod, String serviceClass) {
//		boolean checked = false;
//		String currentClass = this.getClass().getName();
//
//		for(StackTraceElement element : Thread.currentThread().getStackTrace()) {
//			if(currentClass.equals(element.getClassName())) {
//				String callMethod = element.getMethodName();
//				currentMethod = callMethod;
//			}else if(element.getClassName().endsWith(ConstantsRequest.SERVICE_NM)) {
//				String callerClass = element.getClassName();
//				String pattern = "^(?!.*?(?:" + ConstantsRequest.IGNORE_WRD + ")).*."
//						+ ConstantsRequest.SERVICE_NM.toLowerCase() + ".([A-Za-z]+)"
//						+ ConstantsRequest.SERVICE_NM + "$";
//				Pattern r = Pattern.compile(pattern);
//				Matcher m = r.matcher(callerClass);
//				if (m.find()) {
//					serviceClass = m.group(1);
//					checked = true;
//				}
//			}
//			if(checked) {
//				break;
//			}
//		}
//		String mapperNm = StringUtils.toFirstLower(serviceClass) + ConstantsRequest.MAPPER_NM;
//		return (BaseMapper)applicationContext.getBean(mapperNm);
//	}

	private List<ZValue> sqlWithParam(String queryId, ZValue param){

		String currentMethod = "";
		String serviceClass = "";
//		BaseMapper mapper = getMapper(currentMethod, serviceClass);
//		if(mapper != null) {
//			if (queryId == null) {
//				queryId = getDefaultSqlNm(currentMethod, serviceClass);
//			}
//			String methodName = queryId;
//			Method method = ReflectionUtils.findMethod(mapper.getClass(), methodName, ZValue.class);
//			if(method != null) {
//				@SuppressWarnings("unchecked")
//				List<ZValue> result = (List<ZValue>)ReflectionUtils.invokeMethod(method, mapper, new Object[]{param});
//				if(CollectionUtils.isEmpty(result)){
//					return null;
//				}
//				return result;
//			}
//		}
		return new ArrayList<>();
	}

	private String getDefaultSqlNm(String currentMethod, String serviceClass) {
		return currentMethod + serviceClass;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


}
