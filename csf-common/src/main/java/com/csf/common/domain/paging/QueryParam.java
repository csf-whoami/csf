/**
 * 
 */
package com.csf.common.domain.paging;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mba0051
 *
 */
public class QueryParam<T> {

	@NotNull
	@Valid
	@JsonProperty("Query")
	private T query;

	@NotNull
	@Valid
	@JsonProperty("PagingItem")
	private PagingItem pagingItem;

	/**
	 * @return T
	 */
	public T getQuery() {
		return query;
	}

	/**
	 * @param query
	 */
	public void setQuery(T query) {
		this.query = query;
	}

	/**
	 * @return PagingItem
	 */
	public PagingItem getPagingItem() {
		return pagingItem;
	}

	/**
	 * @param pagingItem
	 */
	public void setPagingItem(PagingItem pagingItem) {
		this.pagingItem = pagingItem;
	}
}
