package com.csf.database.mappers;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.mapstruct.Mapper;

import com.csf.base.core.ZValue;
import com.csf.database.providers.MenuProvider;

@Mapper
public interface MenuMapper {

	@SelectProvider(type = MenuProvider.class, method = "fetchMenus")
	List<ZValue> fetchMenuByCondition();
}
