package com.csf.whoami.database.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SearchVO {

    public static final int DEFAULT_PAGING_SIZE = 10;
    public static final int DEFAULT_PAGING_PAGE = 0;

    private String keyword;

    private Long maximum = 99999L;

    private Long minimum = 0L;

    private String usable = "ALL";

    private String startDate;

    private String endDate;

    private Map<String, Object> params = new HashMap<>();

    private int page = 1;
    private int pageSize = DEFAULT_PAGING_SIZE;

    private Long themeTypeId = 1L;

	public void setId(Long fromLong) {
		// TODO Auto-generated method stub
		
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
