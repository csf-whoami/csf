package com.csf.base.core.dao;

import java.util.List;
import java.util.Map;

public interface ISqlDAO<T> {

	/**
	 * List 실행
	 * @param queryId
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public List<T> findAll(String queryId) throws Exception;
	
	public List<T> findAll(String queryId, T param) throws Exception;
	
	public <ID> List<T> findAllByIds(String queryId, Iterable<ID> ids) throws Exception;

	public Map<String, List<T>> findAllMap(String queryId, T param, String columnName) throws Exception;
	
	public <ID> List<T> findById(String queryId, ID id) throws Exception;

	/**
	 * SELECT 실행
	 * @param queryId
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public T findOne(String queryId, T param) throws Exception;
	
	public <ID> T findOneById(String queryId, ID id) throws Exception;

	/**
	 * COUNT 실행
	 * @param queryId
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public long count(String queryId, T param) throws Exception;

	public String selectString(String queryId, T param) throws Exception;

	/**
	 * UPDATE 실행
	 * @param queryId
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public int modify(String queryId, T param) throws Exception;
	
	/**
	 * DELETE 실행
	 * @param queryId
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public int deleteOne(String queryId, T param) throws Exception;
	
	public <ID> int deleteById(String queryId, ID id) throws Exception;
	
	public <ID> int deleteAllByIds(String queryId, Iterable<ID> ids) throws Exception;
	
	public int deleteAll(String queryId, Iterable<T> params) throws Exception;

	/**
	 * INSERT 실행
	 * @param queryId
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public Object save(String queryId, T param) throws Exception;
	
	public void saveAll(String queryId, Iterable<T> params) throws Exception;

}
