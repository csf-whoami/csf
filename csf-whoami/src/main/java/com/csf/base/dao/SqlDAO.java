package com.csf.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class SqlDAO<T> implements ISqlDAO<T> {

	@Override
	public List<T> findAll(String queryId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(String queryId, T param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <ID> List<T> findAllByIds(String queryId, Iterable<ID> ids) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<T>> findAllMap(String queryId, T param, String columnName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <ID> List<T> findById(String queryId, ID id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findOne(String queryId, T param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <ID> T findOneById(String queryId, ID id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(String queryId, T param) throws Exception {
		return 0;
	}

	@Override
	public String selectString(String queryId, T param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteOne(String queryId, T param) throws Exception {
//		log.debug("deleteOne===============:{}", queryId);
		//log.debug("id====================:{}", param);
		return 0;
	}

	// TODO: Need change.
	@Override
	public int delete(String queryId, Object parameterObject) {
		return 0;
	}

	@Override
	public Object save(String queryId, T param) throws Exception {
//			log.debug("save===============:{}", queryId);
		// log.debug("param====================:{}", param);
		return null;
	}

	@Override
	public int update(String queryId, Object parameterObject) {
//        return getSqlSession().update(queryId, parameterObject);
		return 0;
    }

	@Override
	public int modify(String queryId, T param) throws Exception {
//		log.debug("modify===============:{}", queryId);
		//log.debug("id====================:{}", param);
//		return getSqlSession().update(queryId, param);
		return 0;
	}

	@Override
	public <ID> int deleteById(String queryId, ID id) throws Exception {
//		log.debug("deleteById===============:{}", queryId);
		//log.debug("id====================:{}", id);
//		return getSqlSession().delete(queryId, id);
		return 0;
	}
}
