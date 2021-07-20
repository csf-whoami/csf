package com.csf.whoami.service;

import java.util.List;

import com.csf.whoami.database.dto.MenuDomain;

public interface MenuService {
	public List<MenuDomain> getMenus();
}
