/**
 * 
 */
package com.csf.base.domain.paging;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PagingItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Integer pageSize;
	@NotNull
	private Integer pageIndex;
	private String directionSort;
	private String orderBy;

	private Long totalRows;

	/**
	 * @return Long
	 */
	public Long getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows
	 */
	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @return Integer
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return Integer
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex
	 */
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return String
	 */
	public String getDirectionSort() {
		return directionSort;
	}

	/**
	 * @param directionSort
	 */
	public void setDirectionSort(String directionSort) {
		this.directionSort = directionSort;
	}

	/**
	 * @return String
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 
	 */
	public PagingItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pageSize
	 * @param pageIndex
	 * @param directionSort
	 * @param orderBy
	 * @param totalRows
	 */
	public PagingItem(@NotNull Integer pageSize, @NotNull Integer pageIndex, String directionSort, String orderBy) {
		super();
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		this.directionSort = directionSort;
		this.orderBy = orderBy;
	}
}