package com.csf.whoami.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.mapstruct.Mapper;

import com.csf.whoami.database.provider.MenuProvider;
import com.csf.whoami.database.view.MenuView;

@Mapper
public interface MenuMapper {

	@SelectProvider(type = MenuProvider.class, method = "fetchMenus")
	@Results({
			@Result(id = true, property = "id", column = "ID"),
			@Result(property = "name", column = "MENU_NAME"),
			@Result(property = "linkScreen", column = "LINK_SCREEN"),
			@Result(property = "parentMenu", column = "PARENT_MENU"),
			@Result(property = "locale", column = "LOCALE")
		})
	List<MenuView> fetchMenuByCondition();
}
