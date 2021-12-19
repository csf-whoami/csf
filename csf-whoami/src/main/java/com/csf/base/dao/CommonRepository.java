package com.csf.base.dao;

import java.util.List;
import java.util.Map;

import com.csf.base.core.ZValue;

public interface CommonRepository {

	public List<ZValue> findAll(String queryId) throws Exception;

	public List<ZValue> findAll(String queryId, ZValue param) throws Exception;

	public <ID> List<ZValue> findAllByIds(String queryId, Iterable<ID> ids) throws Exception;

	public Map<String, List<ZValue>> findAllMap(String queryId, ZValue param, String columnName) throws Exception;

	public <ID> List<ZValue> findById(String queryId, ID id) throws Exception;

	public ZValue findOne(String queryId, ZValue param) throws Exception;

	public <ID> ZValue findOneById(String queryId, ID id) throws Exception;

	public long count(String queryId, ZValue param) throws Exception;

	public String selectString(String queryId, ZValue param) throws Exception;

	public <ID> int deleteById(String queryId, ID id) throws Exception;

}
