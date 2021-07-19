/**
 * 
 */
package com.csf.base.domain;

import java.io.Serializable;

import com.csf.base.domain.paging.CommonPagingResponse;
/**
 * @author mba0051
 *
 */
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseDataAPI implements Serializable {
	private static final long serialVersionUID = -996509413862521394L;
	private Object data;
	@JsonProperty("total_rows")
	private Object totalRows;
	@JsonAlias({ "errors", "error" })
	private Object errors;
	private String message;
	@JsonProperty("offset")
	private Integer offset;
	@JsonProperty("limit")
	private Integer limit;
	private Boolean success = true;

	public static Builder builder() {
		return new Builder();
	}

	public ResponseDataAPI() {
	}

	ResponseDataAPI(Object data, Object totalRows, Object errors, String message, Integer offset, Integer limit,
			Boolean success) {
		super();
		this.data = data;
		this.totalRows = totalRows;
		this.errors = errors;
		this.message = message;
		this.offset = offset;
		this.limit = limit;
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Object totalRows) {
		this.totalRows = totalRows;
	}

	public Object getErrors() {
		return errors;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	/**
	 * This class is builder pattern for ResponseDataApi
	 */
	public static class Builder {
		private Object data;
		private Object totalRows;
		private Object error;
		private String message;
		private Integer offset;
		private Integer limit;
		private Boolean success = true;

		public Builder data(Object data) {
			if (data instanceof CommonPagingResponse) {
				this.data = ((CommonPagingResponse) data).getData();
				totalRows = ((CommonPagingResponse) data).getTotalRows();
			} else {
				this.data = data;
			}
			return this;
		}

		public Builder totalRows(Object totalRows) {
			this.totalRows = totalRows;
			return this;
		}

		public Builder error(Object error) {
			this.error = error;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder offset(Integer offset) {
			this.offset = offset;
			return this;
		}

		public Builder limit(Integer limit) {
			this.limit = limit;
			return this;
		}

		public Builder success(Boolean success) {
			this.success = success;
			return this;
		}

		public ResponseDataAPI build() {
			return new ResponseDataAPI(data, totalRows, error, message, offset, limit, success);
		}
	}
}
