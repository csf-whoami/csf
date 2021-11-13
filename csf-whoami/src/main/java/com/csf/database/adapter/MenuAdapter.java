package com.csf.database.adapter;

import com.csf.base.domain.MenuVO;
import com.csf.base.utilities.StringUtils;
import com.csf.database.models.TbMenu;

public class MenuAdapter {

	private MenuAdapter() {}
	public static MenuVO dbToDomain(TbMenu entity) {
		if (entity == null) {
			return null;
		}
		MenuVO domain = new MenuVO();
		domain.setId(StringUtils.fromLong(entity.getId()));
		domain.setMenuName(entity.getMenuName());
		domain.setLinkScreen(entity.getLinkScreen());
		domain.setLocale(entity.getLocale());
		domain.setParentMenu(StringUtils.fromLong(entity.getParentMenu()));
		domain.setRoot(entity.getParentMenu() != null);
		return domain;
	}

	public static TbMenu domainToDb(MenuVO domain) {
		if (domain == null) {
			return null;
		}
		TbMenu entity = new TbMenu();
		entity.setId(StringUtils.toLongOrNull(domain.getId()));
		entity.setMenuName(domain.getMenuName());
		entity.setLinkScreen(domain.getLinkScreen());
		entity.setLocale(domain.getLocale());
		entity.setParentMenu(StringUtils.toLongOrNull(domain.getParentMenu()));
		return entity;
	}
}
