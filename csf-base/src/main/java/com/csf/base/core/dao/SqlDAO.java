package com.csf.base.core.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository("SqlDAO")
public class SqlDAO<T> implements ISqlDAO<T> {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<T> findAll(String queryId) throws Exception {
		log.debug("findAll===============:{}", queryId);
		return findAll(queryId, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String queryId, T param) throws Exception {
		log.debug("findAll===============:{}", queryId);
//		return (List<T>)getSqlSession().selectList(queryId, param);
		return new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <ID> List<T> findAllByIds(String queryId, Iterable<ID> ids) throws Exception {
		log.debug("findAllByIds===============:{}", queryId);
		log.debug("ids===================:{}", ids);
//		return (List<T>)getSqlSession().selectList(queryId, ids);
		return new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<T>> findAllMap(String queryId, T param, String columnName) throws Exception {
		log.debug("findAllMap===============:{}", queryId);
		log.debug("param=================:{}", param);
//		List<T> resultList = (List<T>)getSqlSession().selectList(queryId, param);
//		Map<String, List<T>> resultMap = UnpCollectionUtils.convertMap(resultList, "", columnName);
//		return resultMap;
		return new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <ID> List<T> findById(String queryId, ID id) throws Exception {
		log.debug("findById===============:{}", queryId);
		//log.debug("id====================:{}", id);
//		return (List<T>)getSqlSession().selectList(queryId, id);
		return new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findOne(String queryId, T param) throws Exception {
		log.debug("findOne===============:{}", queryId);
		//log.debug("param=================:{}", param);
//		return (T)getSqlSession().selectOne(queryId, param);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <ID> T findOneById(String queryId, ID id) throws Exception {
		log.debug("findOneById===============:{}", queryId);
		//log.debug("id====================:{}", id);
//		Assert.notNull(id);
//		return (T)getSqlSession().selectOne(queryId, id);
		return null;
	}

	@Override
	public long count(String queryId, T param) throws Exception {
//		log.debug("count===============:{}", queryId);
//		//log.debug("id====================:{}", param);
//		Object ret = getSqlSession().selectOne(queryId, param);;
//		if (ret == null) {
//			return 0;
//		}
//		return (Long)ret;
		return 0;
	}

	@Override
	public String selectString(String queryId, T param) throws Exception {
		log.debug("selectString===============:{}", queryId);
		//log.debug("id====================:{}", param);
//		return (String)getSqlSession().selectOne(queryId, param);
		return null;
	}

	@Override
	public int deleteOne(String queryId, T param) throws Exception {
		log.debug("deleteOne===============:{}", queryId);
		//log.debug("id====================:{}", param);
//		return getSqlSession().delete(queryId, param);
		return 0;
	}

	@Override
	public <ID> int deleteById(String queryId, ID id) throws Exception {
		Assert.notNull(id);

		log.debug("deleteById===============:{}", queryId);
		//log.debug("id====================:{}", id);
//		return getSqlSession().delete(queryId, id);
		return 0;
	}

	@Override
	public <ID> int deleteAllByIds(String queryId, Iterable<ID> ids) throws Exception {
		Assert.notNull(ids);

//		log.debug("deleteAllByIds===============:{}", queryId);
//		//log.debug("ids====================:{}", ids);
//		int result = 0;
//		for (ID id : ids) {
//			result += getSqlSession().delete(queryId, id);
//		}
//		return result;
		return 0;
	}

	@Override
	public int deleteAll(String queryId, Iterable<T> params) throws Exception {
		log.debug("deleteAll===============:{}", queryId);
		//log.debug("params====================:{}", params);
//		int result = 0;
//		if (params != null) {
//			for (T param : params) {
//				result += getSqlSession().delete(queryId, param);
//			}
//		}
//		return result;
		return 0;
	}

	@Override
	public Object save(String queryId, T param) throws Exception {
		log.debug("save===============:{}", queryId);
		//log.debug("param====================:{}", param);
//		return getSqlSession().insert(queryId, param);
		return null;
	}

	@Override
	public void saveAll(String queryId, Iterable<T> params) throws Exception {
		log.debug("saveAll===============:{}", queryId);
		//log.debug("params====================:{}", params);
//		if (params != null) {
//			for (T param : params) {
//				getSqlSession().insert(queryId, param);
//			}
//		}
	}

	@Override
	public int modify(String queryId, T param) throws Exception {
		log.debug("modify===============:{}", queryId);
		//log.debug("id====================:{}", param);
//		return getSqlSession().update(queryId, param);
		return 0;
	}

}
