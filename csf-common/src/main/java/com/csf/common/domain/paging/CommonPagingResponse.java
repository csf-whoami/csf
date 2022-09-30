/**
 * 
 */
package com.csf.common.domain.paging;

/**
 * @author mba0051
 *
 */
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.List;

/**
 * The type Common paging response.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CommonPagingResponse implements Serializable {
    private static final long serialVersionUID = 1018502635524561454L;
    private List<Object> data;
    private Long totalRows;

    /**
     * Instantiates a new Common paging response.
     */
    public CommonPagingResponse() {
    }

    /**
     * Instantiates a new Common paging response.
     *
     * @param data      the data
     * @param totalRows the total rows
     */
    public CommonPagingResponse(List<Object> data, Long totalRows) {
        this.data = data;
        this.totalRows = totalRows;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public List<Object> getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(List<Object> data) {
        this.data = data;
    }

    /**
     * Gets total rows.
     *
     * @return the total rows
     */
    public Long getTotalRows() {
        return totalRows;
    }

    /**
     * Sets total rows.
     *
     * @param totalRows the total rows
     */
    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }
}