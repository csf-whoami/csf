package com.csf.base.dao;

import java.util.List;
import java.util.Map;

import com.csf.base.ParameterContext;

public interface ISqlDAO<T> {

	public List<T> findAll(String queryId) throws Exception;
	
	public List<T> findAll(String queryId, T param) throws Exception;
	
	public <ID> List<T> findAllByIds(String queryId, Iterable<ID> ids) throws Exception;

	public Map<String, List<T>> findAllMap(String queryId, T param, String columnName) throws Exception;
	
	public <ID> List<T> findById(String queryId, ID id) throws Exception;

	public T findOne(String queryId, T param) throws Exception;
	
	public <ID> T findOneById(String queryId, ID id) throws Exception;

	public long count(String queryId, T param) throws Exception;

	public String selectString(String queryId, T param) throws Exception;

	public int deleteOne(String queryId, T param) throws Exception;

	public Object save(String queryId, T param) throws Exception;

	public int delete(String queryId, Object parameterObject);

	public int update(String queryId, Object parameterObject);

	public int modify(String queryId, T param) throws Exception;

	public <ID> int deleteById(String queryId, ID id) throws Exception;
}
