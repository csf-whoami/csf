package com.csf.whoami.service;

import java.util.List;

import com.csf.base.domain.MenuVO;

public interface MenuService {
	public List<MenuVO> getMenus();

	public Long registerMenu(MenuVO menu);
}
