package com.csf.base.service.common;

import com.csf.base.CommonListener;
import com.csf.base.dao.BaseRepository;
import com.csf.base.paging.PageQuery;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommonServiceSupport {

	private CommonListener listener;
	private String pageQueryData;
	protected PageQuery pageQuery;

	protected BaseRepository sqlDao;

	public <S> S getSqlDao(Class<S> clazz) {
		try {
			return clazz.cast(sqlDao);
		} catch (ClassCastException e) {
			e.printStackTrace();
			return null;
		}
	}
}
