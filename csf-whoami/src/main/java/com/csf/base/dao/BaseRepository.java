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
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import com.csf.base.constant.ConstantsRequest;
import com.csf.base.core.ZValue;
import com.csf.base.utilities.StringUtils;
import com.csf.database.mappers.BaseMapper;

@Repository
public class BaseRepository<T> implements ISqlDAO<T>, ApplicationContextAware {

	protected ApplicationContext applicationContext;

	@Override
	public List<T> findAll(String queryId) throws Exception {
		System.out.println("Go to findAll");
		return null;
	}

	@Override
	public List<T> findAll(String queryId, T param) throws Exception {
		return sqlWithParam(queryId, param);
	}

	@Override
	public <ID> List<T> findAllByIds(String queryId, Iterable<ID> ids) throws Exception {
		System.out.println("Go to findAllByIds");
		return null;
	}

	@Override
	public Map<String, List<T>> findAllMap(String queryId, T param, String columnName) throws Exception {
		System.out.println("Go to findAllMap");
		return null;
	}

	@Override
	public <ID> List<T> findById(String queryId, ID id) throws Exception {
		System.out.println("Go to findById");
		return null;
	}

	@Override
	public T findOne(String queryId, T param) throws Exception {
		System.out.println("Go to findOne");
		return null;
	}

	@Override
	public <ID> T findOneById(String queryId, ID id) throws Exception {
		System.out.println("Go to findOneById");
		return null;
	}

	@Override
	public long count(String queryId, T param) throws Exception {
		System.out.println("Go to findOneById");
		return 0;
	}

	@Override
	public String selectString(String queryId, T param) throws Exception {
		System.out.println("Go to selectString");
		return null;
	}

	@Override
	public int deleteOne(String queryId, T param) throws Exception {
//		log.debug("deleteOne===============:{}", queryId);
		//log.debug("id====================:{}", param);
		System.out.println("Go to deleteOne");
		return 0;
	}

	// TODO: Need change.
	@Override
	public int delete(String queryId, Object parameterObject) {
		System.out.println("Go to delete");
		return 0;
	}

	@Override
	public Object save(String queryId, T param) throws Exception {
//			log.debug("save===============:{}", queryId);
		// log.debug("param====================:{}", param);
		System.out.println("Go to save");
		return null;
	}

	@Override
	public int update(String queryId, Object parameterObject) {
//        return getSqlSession().update(queryId, parameterObject);
		System.out.println("Go to update");
		return 0;
    }

	@Override
	public int modify(String queryId, T param) throws Exception {
//		log.debug("modify===============:{}", queryId);
		//log.debug("id====================:{}", param);
//		return getSqlSession().update(queryId, param);
		System.out.println("Go to modify");
		return 0;
	}

	@Override
	public <ID> int deleteById(String queryId, ID id) throws Exception {
//		log.debug("deleteById===============:{}", queryId);
		//log.debug("id====================:{}", id);
//		return getSqlSession().delete(queryId, id);
		System.out.println("Go to deleteById");
		return 0;
	}

	private BaseMapper getMapper(String currentMethod, String serviceClass) {
		boolean checked = false;
		String currentClass = this.getClass().getName();

		for(StackTraceElement element : Thread.currentThread().getStackTrace()) {
			if(currentClass.equals(element.getClassName())) {
				String callMethod = element.getMethodName();
				currentMethod = callMethod;
			}else if(element.getClassName().endsWith(ConstantsRequest.SERVICE_NM)) {
				String callerClass = element.getClassName();
				String pattern = "^(?!.*?(?:" + ConstantsRequest.IGNORE_WRD + ")).*."
						+ ConstantsRequest.SERVICE_NM.toLowerCase() + ".([A-Za-z]+)"
						+ ConstantsRequest.SERVICE_NM + "$";
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
		String mapperNm = StringUtils.toFirstLower(serviceClass) + ConstantsRequest.MAPPER_NM;
		return (BaseMapper)applicationContext.getBean(mapperNm);
	}

	private List<T> sqlWithParam(String queryId, T param){
		if(param instanceof ZValue) {
			String currentMethod = "";
			String serviceClass = "";
			BaseMapper mapper = getMapper(currentMethod, serviceClass);
			if(mapper != null) {
				if (queryId == null) {
					queryId = getDefaultSqlNm(currentMethod, serviceClass);
				}
				String methodName = queryId;
				Method method = ReflectionUtils.findMethod(mapper.getClass(), methodName, ZValue.class);
				if(method != null) {
					@SuppressWarnings("unchecked")
					List<T> result = (List<T>)ReflectionUtils.invokeMethod(method, mapper, new Object[]{param});
					if(CollectionUtils.isEmpty(result)){
						return null;
					}
					return result;
				}
			}
		}
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
