package com.csf.whoami.base.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseDataAPI implements Serializable {

	private static final long serialVersionUID = -5209006022014878790L;
	private Object data;
    @JsonProperty("total_rows")
    private Object totalRows;
    private Object error;
    private String message;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("limit")
    private Integer limit;
    private Boolean success = true;

    public static ResponseDataAPI build() {
        return new Builder().build();
    }

    public static Builder builder() {
        return new Builder();
    }

    ResponseDataAPI(Object data, Object totalRows, Object error, String message, Integer offset, Integer limit,
            Boolean success) {
        super();
        this.data = data;
        this.totalRows = totalRows;
        this.error = error;
        this.message = message;
        this.offset = offset;
        this.limit = limit;
        this.success = success;
    }

    /**
     * This class is builder pattern for ResponseDataApi
     */
//    @Setter @Getter
    public static class Builder {
        private Object data;
        private Object totalRows;
        private Object error;
        private String message;
        private Integer offset;
        private Integer limit;
        private Boolean success;

        public Builder data(Object data) {
            this.data = data;
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
