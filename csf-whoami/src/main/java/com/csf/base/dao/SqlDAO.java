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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String selectString(String queryId, T param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
