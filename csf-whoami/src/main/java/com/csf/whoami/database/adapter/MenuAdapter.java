package com.csf.whoami.database.adapter;

import com.csf.base.domain.MenuDomain;
import com.csf.base.utilities.StringUtils;
import com.csf.whoami.database.models.TbMenu;

public class MenuAdapter {

	private MenuAdapter() {}
	public static MenuDomain dbToDomain(TbMenu entity) {
		if (entity == null) {
			return null;
		}
		MenuDomain domain = new MenuDomain();
		domain.setId(StringUtils.fromLong(entity.getId()));
		domain.setMenuName(entity.getMenuName());
		domain.setLinkScreen(entity.getLinkScreen());
		domain.setLocale(entity.getLocale());
		domain.setParentMenu(entity.getParentMenu());
		domain.setRoot(entity.getParentMenu() != null);
		return domain;
	}

	public static TbMenu domainToDb(MenuDomain domain) {
		if (domain == null) {
			return null;
		}
		TbMenu entity = new TbMenu();
		entity.setId(StringUtils.toLongOrNull(domain.getId()));
		entity.setMenuName(domain.getMenuName());
		entity.setLinkScreen(domain.getLinkScreen());
		entity.setLocale(domain.getLocale());
		entity.setParentMenu(domain.getParentMenu());
		return entity;
	}
}
