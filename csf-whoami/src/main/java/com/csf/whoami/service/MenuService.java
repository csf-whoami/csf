package com.csf.whoami.service;

import java.util.List;

import com.csf.base.domain.MenuDomain;

public interface MenuService {
	public List<MenuDomain> getMenus();

	public Long registerMenu(MenuDomain menu);
}
